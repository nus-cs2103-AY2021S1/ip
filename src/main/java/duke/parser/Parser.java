package duke.parser;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.ReminderCommand;
import duke.commands.ToDoCommand;

/**
 * Parses user input.
 */
public class Parser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String EVENT_DELIMITER = "/at";
    private static final String DEADLINE_DELIMITER = "/by";

    /**
     * Parses user input and performs corresponding actions
     * through ui, taskList and storage.
     * @param userInput The line of text keyed in by the user.
     */
    public Command parseUserInput(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Sorry I dont recognize that command, type help for more info!");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        case DoneCommand.COMMAND_WORD:
            return prepareDone(arguments);
        case ReminderCommand.COMMAND_WORD:
            return new ReminderCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    private Command prepareDelete(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new DeleteCommand(index - 1);
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Processes the arguments for the FindCommand.
     * @param arguments Search terms for the find function.
     * @return A FindCommand or an IncorrectCommand when the argument is invalid.
     */
    private Command prepareFind(String arguments) {
        try {
            return new FindCommand(arguments.trim());
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    /**
     * Processes the arguments for the DoneCommand.
     * @param arguments A number indicating which task should be marked as done.
     * @return A DoneCommand or an IncorrectCommand when the argument is invalid.
     */
    private Command prepareDone(String arguments) { // check for number
        try {
            String index = arguments.trim();
            return new DoneCommand(Integer.parseInt(index) - 1);
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    /**
     * Processes the arguments for the DeadlineCommand.
     * @param arguments Arguments for Deadline.
     * @return A DeadlineCommand or an IncorrectCommand when the argument is invalid.
     */
    private Command prepareDeadline(String arguments) {
        try {
            String description = splitDescription(arguments);
            LocalDate date = parseTime(arguments, DEADLINE_DELIMITER);
            return new DeadlineCommand(description, date);
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    /**
     * Processes the arguments for the EventCommand.
     * @param arguments Arguments for Event.
     * @return An EventCommand or an IncorrectCommand when the argument is invalid.
     */
    private Command prepareEvent(String arguments) {
        try {
            String description = splitDescription(arguments);
            LocalDate date = parseTime(arguments, EVENT_DELIMITER);
            return new EventCommand(description, date);
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    /**
     * Processes the arguments for the ToDoCommand.
     * @param arguments Arguments for ToDo.
     * @return A ToDoCommand or an IncorrectCommand when the argument is invalid.
     */
    private Command prepareToDo(String arguments) {
        try {
            return new ToDoCommand(arguments.trim());
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    /**
     * Splits the argument to retrieve the description.
     * @param arguments User input containing only the arguments of the command.
     * @return Description.
     */
    private String splitDescription(String arguments) {
        return arguments.split("/")[0].trim();
    }

    /**
     * Splits the argument based on the delimiter to retrieve the LocalDate.
     * @param arguments User input containing only the arguments of the command.
     * @param delimiter Delimiter for event or deadline. Either "/at" or "/by".
     * @return LocalDate.
     */
    private LocalDate parseTime(String arguments, String delimiter) { // throws a wrong dateformatexception
        String time = arguments.split(delimiter)[1].trim();
        return LocalDate.parse(time);
    }
}
