package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.QueryCommand;
import duke.command.EditCommand;
import duke.command.FileCommand;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A non-instantiable class with static methods to parse a String into a Command or Task.
 */
public class Parser {

    /**
     * Sole constructor. (Not instantiable.)
     */
    private Parser() {}

    /**
     * Parses a specified string and converts it into a Command to be executed.
     *
     * @param string the String from the user input
     * @return the Command parsed from the string
     * @throws DukeException If the string deviates from any of the acceptable formats
     */
    public static Command parse(String string) throws DukeException {
        Scanner parsingScanner = new Scanner(string);
        if (!parsingScanner.hasNext()) {
            throw DukeException.INVALID_COMMAND_EXCEPTION;
        }
        String commandString = parsingScanner.next();
        Command command; // Has to be declared beforehand for correct scope
        switch (commandString.toLowerCase()) {
        case "bye":
            command = new ExitCommand(CommandType.BYE);
            break;
        case "list":
            command = new ListCommand(CommandType.LIST);
            break;
        case "load":
            command = parseFileCommand(CommandType.LOAD, parsingScanner);
            break;
        case "create":
            command = parseFileCommand(CommandType.CREATE, parsingScanner);
            break;
        case "find":
            command = parseQueryCommand(CommandType.FIND, parsingScanner);
            break;
        case "done":
            command = parseEditCommand(CommandType.DONE, parsingScanner);
            break;
        case "delete":
            command = parseEditCommand(CommandType.DELETE, parsingScanner);
            break;
        case "todo":
            command = parseAddCommand(CommandType.TODO, parsingScanner);
            break;
        case "deadline":
            command = parseAddCommand(CommandType.DEADLINE, parsingScanner);
            break;
        case "event":
            command = parseAddCommand(CommandType.EVENT, parsingScanner);
            break;
        default:
            throw DukeException.INVALID_COMMAND_EXCEPTION;
        }
        parsingScanner.close();
        return command;
    }

    /**
     * Returns a FileCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the FileCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate FileCommand.
     * @throws DukeException If the parsing scanner is empty.
     */
    private static FileCommand parseFileCommand(CommandType commandType, Scanner parsingScanner)
            throws DukeException {
        if (!parsingScanner.hasNext()) {
            throw DukeException.EMPTY_FILEPATH_EXCEPTION;
        }
        return new FileCommand(commandType, parsingScanner.nextLine());
    }

    /**
     * Returns a QueryCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the QueryCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate QueryCommand.
     * @throws DukeException If the parsing scanner is empty.
     */
    private static QueryCommand parseQueryCommand(CommandType commandType, Scanner parsingScanner)
            throws DukeException {
        if (!parsingScanner.hasNext()) {
            throw DukeException.INVALID_QUERY_EXCEPTION;
        }
        return new QueryCommand(commandType, parsingScanner.nextLine().trim().toLowerCase());
    }

    /**
     * Returns an EditCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the EditCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate EditCommand.
     * @throws DukeException If the parsing scanner is empty.
     */
    private static EditCommand parseEditCommand(CommandType commandType, Scanner parsingScanner)
            throws DukeException {
        if (!parsingScanner.hasNext()) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
        try {
            int index = Integer.parseInt(parsingScanner.next());
            return new EditCommand(commandType, index);
        } catch (NumberFormatException e) {
            throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
        }
    }

    /**
     * Returns a AddCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the AddCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate AddCommand.
     * @throws DukeException If the parsing scanner is empty.
     */
    private static AddCommand parseAddCommand(CommandType commandType, Scanner parsingScanner)
            throws DukeException {
        if (!parsingScanner.hasNext()) {
            throw DukeException.INVALID_TASK_DESCRIPTION_EXCEPTION;
        }
        String line = parsingScanner.nextLine().trim();
        if (commandType.equals(CommandType.TODO)) {
            return new AddCommand(commandType, line);
        }
        if (commandType.equals(CommandType.DEADLINE)) {
            return splitAndParseAddCommand(commandType, line, "( /by )",
                    DukeException.INVALID_DEADLINE_FORMAT_EXCEPTION);
        }
        if (commandType.equals(CommandType.EVENT)) {
            return splitAndParseAddCommand(commandType, line, "( /at )",
                    DukeException.INVALID_EVENT_FORMAT_EXCEPTION);
        } else {
            throw DukeException.INVALID_COMMAND_EXCEPTION;
        }
    }

    /**
     * Returns the specific AddCommand after splitting the line according to the regular expression.
     *
     * @param commandType The type of the AddCommand.
     * @param line The line to be parsed and split.
     * @param regex The regular expression used for splitting the line.
     * @param exception The DukeException to be thrown if the parsing failed.
     * @return The appropriate AddCommand.
     * @throws DukeException If the line does not contain the regular expression.
     */
    private static AddCommand splitAndParseAddCommand(
            CommandType commandType, String line, String regex, DukeException exception) throws DukeException {
        try {
            String[] details = line.split(regex, 2);
            return new AddCommand(commandType, details[0], details[1]);
        } catch (IndexOutOfBoundsException e) {
            throw exception;
        }
    }

    /**
     * Parses a specified line from a File and converts it into a Task.
     *
     * @param line a String read from a File
     * @return the Task parsed from the line
     * @throws DukeException If the line deviates from any of the acceptable formats
     */
    public static Task parseTaskFromFile(String line) throws DukeException {
        assert !line.isEmpty();
        Task task; // Has to be declared beforehand for correct scope
        Scanner parsingScanner = new Scanner(line);
        String type; // Has to be declared beforehand for correct scope
        String description;
        int isDoneInteger; // Has to be declared beforehand for correct scope
        try {
            type = parsingScanner.next();
            parsingScanner.next();
            isDoneInteger = parsingScanner.nextInt();
            parsingScanner.next();
            description = parsingScanner.nextLine().trim();
            parsingScanner.close();
        } catch (NoSuchElementException e) {
            throw DukeException.FILE_PARSING_EXCEPTION;
        }
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            String[] details = description.split("( by )", 2);
            task = new Deadline(details[0], details[1]);
            break;
        case "E":
            details = description.split("( at )", 2);
            task = new Event(details[0], details[1]);
            break;
        default:
            throw DukeException.FILE_PARSING_EXCEPTION;
        }
        if (isDoneInteger == 1) {
            task.markAsDone();
        }
        return task;
    }

}
