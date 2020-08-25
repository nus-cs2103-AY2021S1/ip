package src.main.java.duke.parser;

import src.main.java.duke.commands.*;

import static src.main.java.duke.commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static src.main.java.duke.commons.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern EVENT_TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<description>[^/]+)+\\s+(\\/at)+\\s+"
                    + "(?<duedate>[\\d\\d\\d-\\d\\d\\-\\d\\d\\d+\\s+\\d\\d:\\d\\d]+)");

    public static final Pattern DEADLINE_TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter
                                                                 // prefixes
            Pattern.compile("(?<description>[^/]+)+\\s+(\\/by)+\\s+"
                    + "(?<duedate>[\\d\\d\\d-\\d\\d\\-\\d\\d\\d+\\s+\\d\\d:\\d\\d]+)");

    public static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<description>[^/]+)");

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case AddTodoCommand.COMMAND_WORD:
                return prepareAddTodo(arguments);
            case AddEventCommand.COMMAND_WORD:
                return prepareAddEvent(arguments);
            case AddDeadlineCommand.COMMAND_WORD:
                return prepareAddDeadline(arguments);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case MarkDoneCommand.COMMAND_WORD:
                return prepareMarkDone(arguments);
            default:
                return new HelpCommand();
        }
    }

    /**
     * Parses arguments in the context of the add task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddEvent(String args) {
        final Matcher matcher = EVENT_TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }
        return new AddEventCommand(matcher.group("description"), matcher.group("duedate"));
    }

    /**
     * Parses arguments in the context of the add task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddTodo(String args) {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }
        return new AddTodoCommand(matcher.group("description"));
    }

    /**
     * Parses arguments in the context of the add task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddDeadline(String args) {
        final Matcher matcher = DEADLINE_TASK_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDeadlineCommand.MESSAGE_USAGE));
        }
        return new AddDeadlineCommand(matcher.group("description"), matcher.group("duedate"));
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareMarkDone(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkDoneCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException        if no region of the args string could be found
     *                               for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

}
