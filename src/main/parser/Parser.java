package main.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import main.command.Command;
import main.command.DeadlineCommand;
import main.command.DeleteCommand;
import main.command.DoneCommand;
import main.command.EventCommand;
import main.command.ExitCommand;
import main.command.FindCommand;
import main.command.ListCommand;
import main.command.TodoCommand;
import main.exception.EmptyMessageException;
import main.exception.InvalidDateException;
import main.exception.InvalidDeadlineFormatException;
import main.exception.InvalidEventFormatException;
import main.exception.InvalidTaskException;
import main.exception.UnknownCommandException;

/**
 * Handles the parsing of user inputs.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static LocalDateTime toDateTime(String dateTime) throws InvalidDateException {
        String[] dateTimeSplit = dateTime.split(" ");
        boolean isWrongDateTimeFormat = dateTimeSplit.length != 2;

        if (isWrongDateTimeFormat) {
            throw new InvalidDateException("Your date needs to"
                    + " have this format:\n\"YYYY-MM-DD HHMM\"");
        }

        String[] date = dateTimeSplit[0].split("-");
        String time = dateTimeSplit[1];
        boolean isWrongDateFormat = date.length != 3;
        boolean isWrongTimeFormat = time.length() != 4;

        if (isWrongDateFormat) {
            throw new InvalidDateException("Your date needs to"
                    + " have this format:\n\"YYYY-MM-DD\"");
        }
        if (isWrongTimeFormat) {
            throw new InvalidDateException("Your time needs to"
                    + " have this format:\n\"HHMM\"");
        }

        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));

            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidDateException("Please check that you've"
                    + " entered the date and time correctly");
        }
    }

    private static Command parseAdd(String[] input)
            throws InvalidDeadlineFormatException,
                InvalidDateException,
                InvalidEventFormatException,
                UnknownCommandException {
        assert(input.length > 1);

        String command = input[0];
        String description = input[1];
        String[] nameAndTime;
        boolean isSingleArgument;

        switch (command) {
        case COMMAND_TODO:
            return new TodoCommand(description);
        case COMMAND_DEADLINE:
            nameAndTime = description.split(" /by ", 2);
            isSingleArgument = nameAndTime.length == 1;

            if (isSingleArgument) {
                throw new InvalidDeadlineFormatException();
            }

            return new DeadlineCommand(nameAndTime[0], toDateTime(nameAndTime[1]));
        case COMMAND_EVENT:
            nameAndTime = description.split(" /at ", 2);
            isSingleArgument = nameAndTime.length == 1;

            if (isSingleArgument) {
                throw new InvalidEventFormatException();
            }

            return new EventCommand(nameAndTime[0], toDateTime(nameAndTime[1]));
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the input in the form of a string array and returns the
     * corresponding Command.
     * @param input the user input as a string array.
     * @return a Command based on the input.
     * @throws InvalidTaskException if the selected task does not exist.
     * @throws EmptyMessageException if the description of the task is empty.
     * @throws UnknownCommandException if the command given is unknown.
     * @throws InvalidDateException if the deadline or event dates are invalid.
     * @throws InvalidDeadlineFormatException if the format of deadline
     * command is invalid.
     * @throws InvalidEventFormatException if the format of event is invalid.
     */
    public static Command parse(String[] input)
            throws InvalidTaskException,
                EmptyMessageException,
                UnknownCommandException,
                InvalidDateException,
                InvalidDeadlineFormatException,
                InvalidEventFormatException {
        String command = input[0];
        boolean isSingleArgument = input.length == 1;
        int taskNum;

        try {
            switch (command) {
            case COMMAND_EXIT:
                return new ExitCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_DONE:
                if (isSingleArgument) {
                    throw new InvalidTaskException();
                }

                taskNum = Integer.parseInt(input[1]);

                return new DoneCommand(taskNum);
            case COMMAND_DELETE:
                if (isSingleArgument) {
                    throw new InvalidTaskException();
                }

                taskNum = Integer.parseInt(input[1]);

                return new DeleteCommand(taskNum);
            case COMMAND_TODO:
            case COMMAND_DEADLINE:
            case COMMAND_EVENT:
                if (isSingleArgument) {
                    throw new EmptyMessageException(command);
                }

                return parseAdd(input);
            case COMMAND_FIND:
                if (isSingleArgument) {
                    return new FindCommand("");
                }

                return new FindCommand(input[1]);
            default:
                throw new UnknownCommandException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }
}
