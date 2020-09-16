package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.StatCommand;
import duke.utils.Messages;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_TODO_FORMAT = Pattern.compile("(?<description>.*)");
    public static final Pattern ADD_DEADLINE_FORMAT = Pattern.compile("(?<description>[^/]+)/by(?<deadline>.*)");
    public static final Pattern ADD_EVENT_FORMAT = Pattern.compile("(?<description>[^/]+)/at(?<dateTime>.*)");

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>[0-9]+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {

        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(arguments);

        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(arguments);

        case DoneCommand.COMMAND_WORD:
            return prepareDone(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case StatCommand.COMMAND_WORD:
            return new StatCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Parses arguments in the context of the add todo command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddTodo(String args) {
        final Matcher matcher = ADD_TODO_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTodoCommand.MESSAGE_USAGE));
        }
        if (matcher.group("description").isBlank()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_BLANK_STRING);
        }
        return new AddTodoCommand(matcher.group("description"));
    }

    /**
     * Parses arguments in the context of the add deadline command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddDeadline(String args) {
        final Matcher matcher = ADD_DEADLINE_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDeadlineCommand.MESSAGE_USAGE));
        }

        try {
            LocalDateTime deadline = getLocalDateTime(matcher.group("deadline").trim());
            return new AddDeadlineCommand(matcher.group("description"), deadline);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Parses arguments in the context of the add event command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddEvent(String args) {
        final Matcher matcher = ADD_EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEventCommand.MESSAGE_USAGE));
        }

        try {
            LocalDateTime dateTime = getLocalDateTime(matcher.group("dateTime").trim());
            return new AddEventCommand(matcher.group("description"), dateTime);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Returns the date and time of tasks in a LocalDateTime object.
     * @param dateTimeStr date time string
     * @return the LocalDateTime object
     * @throws DateTimeParseException when date time string format is incorrect
     */
    private LocalDateTime getLocalDateTime(String dateTimeStr) throws DateTimeParseException {
        String newDateTimeStr = dateTimeStr.replace('/', '-');
        if (dateTimeStr.length() < 11) { // no timing specified
            newDateTimeStr += " 23:59";
        } else {
            StringBuilder sb = new StringBuilder(newDateTimeStr);
            sb.insert(newDateTimeStr.length() - 2, ":");
            newDateTimeStr = sb.toString();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(newDateTimeStr, formatter);
    }

    /**
     * Parses arguments in the context of the done task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDone(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args.trim());
            assert targetIndex > 0; // Target task index should be larger than 0.
            return new DoneCommand(targetIndex);
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DoneCommand.MESSAGE_USAGE));
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args.trim());
            assert targetIndex > 0; // Target task index should be larger than 0.
            return new DeleteCommand(targetIndex);
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse.");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

}
