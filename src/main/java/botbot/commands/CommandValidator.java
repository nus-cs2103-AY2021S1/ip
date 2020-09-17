package botbot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import botbot.tasks.Deadline;
import botbot.tasks.Event;
import botbot.utils.Parser;

/**
 * Checker for the validity of user input as Botbot commands.
 */
public class CommandValidator {
    public static final String ERROR_MESSAGE_INVALID_TASK_ID = "invalid task number!";
    public static final String ERROR_MESSAGE_NO_SUCH_COMMAND = "sorry, I don't know what that means!";
    
    private static final String ERROR_MESSAGE_EMPTY = "%s cannot be empty!";
    private static final String ERROR_MESSAGE_EMPTY_DESCRIPTION = String.format(ERROR_MESSAGE_EMPTY,
            "the description of a task");
    private static final String ERROR_MESSAGE_EMPTY_EDIT = String.format(ERROR_MESSAGE_EMPTY,
            "the content to be edited");
    private static final String ERROR_MESSAGE_EMPTY_SEARCH = String.format(ERROR_MESSAGE_EMPTY,
            "the search keyword");
    private static final String ERROR_MESSAGE_EMPTY_TASK_ID = String.format(ERROR_MESSAGE_EMPTY,
            "the task number to be %s");
    private static final String ERROR_MESSAGE_EMPTY_TASK_ID_DELETE = String.format(ERROR_MESSAGE_EMPTY_TASK_ID,
            "deleted");
    private static final String ERROR_MESSAGE_EMPTY_TASK_ID_DONE = String.format(ERROR_MESSAGE_EMPTY_TASK_ID,
            "marked as done");
    private static final String ERROR_MESSAGE_EMPTY_TASK_ID_EDIT = String.format(ERROR_MESSAGE_EMPTY_TASK_ID,
            "edited");

    private static final String ERROR_MESSAGE_INVALID_FORMAT = "invalid %s format! please follow '%s'!";
    private static final String ERROR_MESSAGE_INVALID_FORMAT_DATETIME = String.format(ERROR_MESSAGE_INVALID_FORMAT,
            "date-time", "D-M-YYYY [HHmm] (eg. 17-3-2020 0945 or 3-4-2020 with no time specified)");
    private static final String ERROR_MESSAGE_INVALID_FORMAT_DEADLINE = String.format(ERROR_MESSAGE_INVALID_FORMAT,
            "command", Deadline.COMMAND_FORMAT);
    private static final String ERROR_MESSAGE_INVALID_FORMAT_EDIT = String.format(ERROR_MESSAGE_INVALID_FORMAT,
            "command", EditCommand.COMMAND_FORMAT);
    private static final String ERROR_MESSAGE_INVALID_FORMAT_EVENT = String.format(ERROR_MESSAGE_INVALID_FORMAT,
            "command", Event.COMMAND_FORMAT);

    private static final Pattern FORMAT_ARG_ADD_DEADLINE = Pattern.compile("(?<description>.*) /by (?<by>.*)");
    private static final Pattern FORMAT_ARG_ADD_EVENT = Pattern.compile("(?<description>.*) /at (?<at>.*)");
    private static final Pattern FORMAT_ARGS_EDIT = Pattern.compile("(?<id>\\d+)(\\h+/d\\h+(?<description>.*?))?"
        + "(\\h+/at\\h+(?<at>.*?))?(\\h+/by\\h+(?<by>.*))?");

    /**
     * Attempts to create a DeadlineCommand.
     *
     * @param args Arguments to create deadline.
     * @return EventCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryAddDeadline(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_DESCRIPTION);
        }

        Matcher matcher = FORMAT_ARG_ADD_DEADLINE.matcher(args);
        if (!matcher.matches()) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_FORMAT_DEADLINE);
        }

        String description = matcher.group("description").trim();
        String byStr = matcher.group("by").trim();
        
        try {
            LocalDateTime by = Parser.parseDateTime(byStr);
            assert !description.isBlank() : "Blank description";
            return new DeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_FORMAT_DATETIME);
        }
    }

    /**
     * Attempts to create an EventCommand.
     *
     * @param args Arguments to create event.
     * @return EventCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryAddEvent(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_DESCRIPTION);
        }

        Matcher matcher = FORMAT_ARG_ADD_EVENT.matcher(args);
        if (!matcher.matches()) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_FORMAT_EVENT);
        }

        String description = matcher.group("description").trim();
        String atStr = matcher.group("at").trim();
        
        try {
            LocalDateTime at = Parser.parseDateTime(atStr);
            assert !description.isBlank() : "Blank description";
            return new EventCommand(description, at);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_FORMAT_DATETIME);
        }
    }

    /**
     * Attempts to create a TodoCommand.
     *
     * @param args Arguments to create to-do.
     * @return TodoCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryAddTodo(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_DESCRIPTION);
        }
        return new TodoCommand(args);
    }
    
    /**
     * Attempts to create a DeleteCommand.
     *
     * @param args Arguments to delete task.
     * @return DeleteCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryDelete(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_TASK_ID_DELETE);
        }

        try {
            int id = Parser.parseCommandId(args);
            return new DeleteCommand(id);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_TASK_ID);
        }
    }

    /**
     * Attempts to create an EditCommand.
     *
     * @param args Arguments to edit task.
     * @return EditCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryEdit(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_TASK_ID_EDIT);
        }
        
        Matcher matcher = FORMAT_ARGS_EDIT.matcher(args.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_FORMAT_EDIT);
        }
        String idStr = matcher.group("id").trim();
        
        Optional<String> description = Optional.ofNullable(matcher.group("description"))
                .map(String::trim);
        Optional<String> atStr = Optional.ofNullable(matcher.group("at"))
                .map(String::trim);
        Optional<String> byStr = Optional.ofNullable(matcher.group("by"))
                .map(String::trim);

        try {
            int id = Parser.parseCommandId(idStr);
            if (description.isEmpty() && atStr.isEmpty() && byStr.isEmpty()) {
                return new InvalidCommand(ERROR_MESSAGE_EMPTY_EDIT);
            }
            Optional<LocalDateTime> at = atStr.map(Parser::parseDateTime);
            Optional<LocalDateTime> by = byStr.map(Parser::parseDateTime);
            return new EditCommand(id, description, at, by);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_TASK_ID);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_FORMAT_DATETIME);
        }
    }

    /**
     * Attempts to create a FindCommand.
     *
     * @param args Arguments to find task(s).
     * @return FindCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryFind(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_SEARCH);
        }
        return new FindCommand(args);
    }

    /**
     * Attempts to create a DoneCommand.
     *
     * @param args Arguments to mark task as done.
     * @return DoneCommand if arguments given are valid, InvalidCommand otherwise.
     */
    public static Command tryMarkAsDone(String args) {
        if (args.isBlank()) {
            return new InvalidCommand(ERROR_MESSAGE_EMPTY_TASK_ID_DONE);
        }

        try {
            int id = Parser.parseCommandId(args);
            return new MarkAsDoneCommand(id);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ERROR_MESSAGE_INVALID_TASK_ID);
        }
    }
}
