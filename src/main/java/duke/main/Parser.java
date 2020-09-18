package duke.main;

import java.util.ArrayList;
import java.util.Arrays;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import duke.exception.DeadlineIncompleteException;
import duke.exception.DeadlineMissingDateException;
import duke.exception.DeleteIncompleteException;
import duke.exception.DeleteOutOfListException;
import duke.exception.DoneIncompleteException;
import duke.exception.DoneOutOfListException;
import duke.exception.DukeException;
import duke.exception.EventIncompleteException;
import duke.exception.EventMissingDateException;
import duke.exception.FindIncompleteException;
import duke.exception.InvalidFormatTaskNumberException;
import duke.exception.InvalidInputException;
import duke.exception.NoInputException;
import duke.exception.TodoIncompleteException;
import duke.exception.UnknownInputException;

/**
 * Parser is used to make sense the input from the user and convert it to executable command.
 */
public class Parser {
    /** Array that stores valid commands as String. **/
    private static final String[] COMMANDS = {"list", "done", "deadline", "event", "todo", "delete", "bye", "find"};
    /** ArrayList that stores valid commands as String. **/
    private static final ArrayList<String> VALID_COMMANDS = new ArrayList<>(Arrays.asList(COMMANDS));

    /**
     * Checks the validity of a command.
     *
     * @param command Command as a String.
     * @return true if the command is not valid, false otherwise.
     */
    private static boolean isNotValidCommand(String command) {
        return !VALID_COMMANDS.contains(command);
    }

    /**
     * Checks the validity of a task number.
     *
     * @param taskNumber The task number as integer.
     * @return true if the task number is zero or negative, otherwise false.
     */
    private static boolean isNotValidTaskNumber(int taskNumber) {
        return taskNumber <= 0;
    }

    /**
     * Checks the correctness of the input by the user.
     *
     * @param input The input from the user.
     * @throws InvalidInputException If user's input is invalid.
     */
    public static void checkInput(String... input) throws InvalidInputException {
        checkInvalidCommand(input);
        checkIncompleteCommand(input);
        checkInvalidTaskNumber(input);
        checkMissingDate(input);
    }

    /**
     * Check for empty or invalid command name from the user input.
     *
     * @param input The input from the user.
     * @throws NoInputException If the input is empty or just whitespace.
     * @throws UnknownInputException If the parser does not know the input.
     */
    private static void checkInvalidCommand(String[] input) throws NoInputException, UnknownInputException {
        // Check for empty input.
        if (input.length == 0) {
            throw new NoInputException();
        }

        // Store the first word from the input which should be the command type.
        String command = input[0];

        // Check for empty input.
        if (command.equals("")) {
            throw new NoInputException();
        }

        // Check for invalid command.
        if (isNotValidCommand(command)) {
            throw new UnknownInputException();
        }
    }

    /**
     * Checks for incomplete input.
     *
     * @param input The input from the user.
     * @throws DoneIncompleteException If user only specifies "done".
     * @throws DeadlineIncompleteException If user only specifies "deadline".
     * @throws EventIncompleteException If user only specifies "event".
     * @throws TodoIncompleteException If user only specifies "todo".
     * @throws DeleteIncompleteException If user only specifies "delete".
     * @throws FindIncompleteException If user only specifies "find".
     */
    private static void checkIncompleteCommand(String[] input) throws DoneIncompleteException,
            DeadlineIncompleteException, EventIncompleteException, TodoIncompleteException,
                    DeleteIncompleteException, FindIncompleteException {
        // Store the first word from the input which should be the command type.
        String command = input[0];
        // Check for incomplete command.
        if (input.length == 1) {
            switch (command) {
            case "done":
                throw new DoneIncompleteException();
            case "deadline":
                throw new DeadlineIncompleteException();
            case "event":
                throw new EventIncompleteException();
            case "todo":
                throw new TodoIncompleteException();
            case "delete":
                throw new DeleteIncompleteException();
            case "find":
                throw new FindIncompleteException();
            default:
                break;
            }
        }
    }

    /**
     * Checks for invalid task number.
     *
     * @param input The input from the user.
     * @throws InvalidFormatTaskNumberException If the task number is not an integer.
     * @throws DoneOutOfListException If the task number is zero or negative.
     */
    private static void checkInvalidTaskNumber(String[] input) throws InvalidFormatTaskNumberException,
        DoneOutOfListException, DeleteOutOfListException {
        // Store the first word from the input which should be the command type.
        String command = input[0];
        // Check for invalid task number.
        if (command.equals("done")) {
            try {
                Integer.parseInt(input[1]);
            } catch (NumberFormatException e) {
                throw new InvalidFormatTaskNumberException();
            }
            int taskNumber = Integer.parseInt(input[1]);
            if (isNotValidTaskNumber(taskNumber)) {
                throw new DoneOutOfListException();
            }
        }

        // Check for invalid task number.
        if (command.equals("delete")) {
            try {
                Integer.parseInt(input[1]);
            } catch (NumberFormatException e) {
                throw new InvalidFormatTaskNumberException();
            }
            int taskNumber = Integer.parseInt(input[1]);
            if (isNotValidTaskNumber(taskNumber)) {
                throw new DeleteOutOfListException();
            }
        }
    }

    /**
     * Checks for missing dates.
     *
     * @param input The input from the user.
     * @throws InvalidInputException If the user does not input the date of the task.
     */
    private static void checkMissingDate(String[] input) throws InvalidInputException {
        // Store the first word from the input which should be the command type.
        String command = input[0];
        // Check for missing date.
        if (command.equals("deadline")) {
            for (String s : input) {
                if (s.equals("/by")) {
                    return;
                }
            }
            throw new DeadlineMissingDateException();
        }

        // Check for missing date.
        if (command.equals("event")) {
            for (String s : input) {
                if (s.equals("/at")) {
                    return;
                }
            }
            throw new EventMissingDateException();
        }
    }

    /**
     * Parses input that starts with "bye".
     *
     * @return A ByeCommand.
     */
    private static Command parseInputThatStartWithBye() {
        return new ByeCommand();
    }

    /**
     * Parses input that starts with "list".
     *
     * @return A ListCommand.
     */
    private static Command parseInputThatStartWithList() {
        return new ListCommand();
    }

    /** Parses input that starts with "find".
     *
     * @param input The user input as array String.
     * @return A FindCommand.
     */
    private static Command parseInputThatStartWithFind(String[] input) {
        return new FindCommand(input[1]);
    }

    /** Parses input that starts with "done"
     *
     * @param input The user input as array String.
     * @return A DoneCommand.
     */
    private static Command parseInputThatStartWithDone(String[] input) {
        int taskNumber = Integer.parseInt(input[1]);
        return new DoneCommand(taskNumber);
    }

    /**
     * Parses input that starts with "delete".
     *
     * @param input The user input as array String.
     * @return A DeleteCommand.
     */
    private static Command parseInputThatStartWithDelete(String[] input) {
        int taskNumber = Integer.parseInt(input[1]);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Parses input that starts with "todo".
     *
     * @param input The user input as array String.
     * @return A TodoCommand.
     */
    private static Command parseInputThatStartWithTodo(String[] input) {
        String description = extractDescription(input, "");
        return new TodoCommand(description);
    }

    /**
     * Parses input that starts with "deadline".
     *
     * @param input The user input as array String.
     * @return A DeadlineCommand.
     */
    private static Command parseInputThatStartWithDeadline(String[] input) {
        String description = extractDescription(input, "/by");
        String date = extractDate(input, "/by");
        return new DeadlineCommand(description, date);
    }

    /**
     * Parses input that starts with "event".
     *
     * @param input The user input as array String.
     * @return An EventCommand.
     */
    private static Command parseInputThatStartWithEvent(String[] input) {
        String description = extractDescription(input, "/at");
        String date = extractDate(input, "/at");
        return new EventCommand(description, date);
    }

    /**
     * Extracts the description of a task.
     *
     * @param input The user input as array String.
     * @param stop String to indicate the end of the the description.
     * @return The description of a task as a String.
     */
    private static String extractDescription(String[] input, String stop) {
        StringBuilder description = new StringBuilder();

        for (int i = 1; i < input.length; i++) {
            String s = input[i];
            // Stop building description when limit is encountered.
            if (s.equals(stop)) {
                break;
            }
            // Append a whitespace after each word.
            if (description.length() > 0) {
                description.append(" ");
            }
            description.append(s);
        }
        return description.toString();
    }

    /**
     * Extracts the date of a task.
     *
     * @param input The user input as array String.
     * @param start String to indicate the start of the date.
     * @return The date of a task as a String.
     */
    private static String extractDate(String[] input, String start) {
        boolean shouldTake = false;
        StringBuilder date = new StringBuilder();

        for (int i = 1; i < input.length; i++) {
            String s = input[i];
            // Start building date when limit is encountered.
            if (s.equals(start)) {
                shouldTake = true;
                continue;
            }
            if (shouldTake) {
                date.append(s);
            }
        }
        return date.toString();
    }
    /**
     * Translates user input into executable Command.
     *
     * @param input The input from the user.
     * @return Command that will be executed.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String ... input) throws DukeException {
        // Check command input
        checkInput(input);
        assert input.length > 0;
        String commandType = input[0];
        switch (commandType) {
        case "bye":
            return parseInputThatStartWithBye();
        case "list":
            return parseInputThatStartWithList();
        case "find": {
            return parseInputThatStartWithFind(input);
        }
        case "done": {
            return parseInputThatStartWithDone(input);
        }
        case "delete": {
            return parseInputThatStartWithDelete(input);
        }
        case "todo": {
            return parseInputThatStartWithTodo(input);
        }
        case "deadline": {
            return parseInputThatStartWithDeadline(input);
        }
        case "event": {
            return parseInputThatStartWithEvent(input);
        }
        default: {
            throw new UnknownInputException();
        }
        }
    }
}
