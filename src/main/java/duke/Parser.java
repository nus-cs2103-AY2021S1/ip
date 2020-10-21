package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FileCommand;
import duke.command.FileException;
import duke.command.FinanceCommand;
import duke.command.ListCommand;
import duke.command.QueryCommand;
import duke.command.UserInputException;
import duke.financial.Category;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A non-instantiable class with static methods to parse strings into processable information.
 */
public class Parser {

    /**
     * Sole constructor. (Not instantiable.)
     */
    private Parser() {}

    /**
     * Parses a specified string and converts it into a Command to be executed.
     *
     * @param string the String from the user input.
     * @return the Command parsed from the string.
     * @throws DukeException If the string deviates from any of the acceptable formats.
     */
    public static Command parse(String string) throws DukeException {
        Scanner parsingScanner = new Scanner(string);
        if (!parsingScanner.hasNext()) {
            throw UserInputException.INVALID_COMMAND;
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
        case "new":
            command = parseFinanceCommand(CommandType.NEW, parsingScanner);
            break;
        case "remove":
            command = parseFinanceCommand(CommandType.REMOVE, parsingScanner);
            break;
        case "add":
            command = parseFinanceCommand(CommandType.ADD, parsingScanner);
            break;
        case "reduce":
            command = parseFinanceCommand(CommandType.REDUCE, parsingScanner);
            break;
        case "rename":
            command = parseFinanceCommand(CommandType.RENAME, parsingScanner);
            break;
        case "display":
            command = new ListCommand(CommandType.DISPLAY);
            break;
        case "unload":
            command = new FileCommand(CommandType.UNLOAD);
            break;
        default:
            throw UserInputException.INVALID_COMMAND;
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
     * @throws FileException If the parsing scanner is empty, or if the user input deviates from the
     * accepted formats.
     */
    private static FileCommand parseFileCommand(CommandType commandType, Scanner parsingScanner)
            throws FileException {
        if (!parsingScanner.hasNext()) {
            throw FileException.INVALID_FILE_SPECIFICATION;
        }
        String fileType = parsingScanner.next().toLowerCase();
        boolean isTask = fileType.equals("task");
        boolean isFinance = fileType.equals("finance");
        if (!isTask & !isFinance) {
            throw FileException.INVALID_FILE_SPECIFICATION;
        }
        if (!parsingScanner.hasNext()) {
            throw FileException.INVALID_FILE_SPECIFICATION;
        }
        String filepath = parsingScanner.nextLine().trim();
        return new FileCommand(commandType, fileType, filepath);
    }

    /**
     * Returns a QueryCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the QueryCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate QueryCommand.
     * @throws UserInputException If the parsing scanner is empty.
     */
    private static QueryCommand parseQueryCommand(CommandType commandType, Scanner parsingScanner)
            throws UserInputException {
        if (!parsingScanner.hasNext()) {
            throw UserInputException.EMPTY_QUERY;
        }
        return new QueryCommand(commandType, parsingScanner.nextLine().trim().toLowerCase());
    }

    /**
     * Returns an EditCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the EditCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate EditCommand.
     * @throws DukeException If the parsing scanner is empty, or if the user input deviates from the
     * accepted formats.
     */
    private static EditCommand parseEditCommand(CommandType commandType, Scanner parsingScanner)
            throws DukeException {
        if (!parsingScanner.hasNext()) {
            throw UserInputException.INVALID_TASK_INDEX;
        }
        try {
            int index = Integer.parseInt(parsingScanner.next());
            return new EditCommand(commandType, index);
        } catch (NumberFormatException e) {
            throw UserInputException.INVALID_TASK_INDEX;
        }
    }

    /**
     * Returns an AddCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the AddCommand.
     * @param parsingScanner The scanner containing the input to be parsed.
     * @return The appropriate AddCommand.
     * @throws DukeException If the parsing scanner is empty, or if the user input deviates from
     * the acceptable formats.
     */
    private static AddCommand parseAddCommand(CommandType commandType, Scanner parsingScanner)
            throws DukeException {
        if (!parsingScanner.hasNext()) {
            throw UserInputException.INVALID_TASK_DESCRIPTION;
        }
        String line = parsingScanner.nextLine().trim();
        if (commandType.equals(CommandType.TODO)) {
            return new AddCommand(commandType, line);
        }
        if (commandType.equals(CommandType.DEADLINE)) {
            return splitAndParseAddCommand(commandType, line, "( /by )",
                    UserInputException.INVALID_DEADLINE_FORMAT);
        }
        if (commandType.equals(CommandType.EVENT)) {
            return splitAndParseAddCommand(commandType, line, "( /at )",
                    UserInputException.INVALID_EVENT_FORMAT);
        } else {
            throw UserInputException.INVALID_COMMAND;
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
     * Returns a FinanceCommand from parsing the input in the parsing scanner.
     *
     * @param commandType The type of the FinanceCommand.
     * @param scanner The scanner containing the input to be parsed.
     * @return The appropriate FinanceCommand.
     * @throws DukeException If the parsing scanner is empty, or if the user input deviates from the
     * accepted formats.
     */
    private static FinanceCommand parseFinanceCommand(CommandType commandType, Scanner scanner) throws DukeException {
        if (!scanner.hasNext()) {
            throw UserInputException.EMPTY_CATEGORY;
        }
        String name = scanner.next();
        FinanceCommand financeCommand;
        switch (commandType) {
        case NEW:
            financeCommand = new FinanceCommand(CommandType.NEW, name);
            break;
        case REMOVE:
            financeCommand = new FinanceCommand(CommandType.REMOVE, name);
            break;
        case ADD:
            if (!scanner.hasNextDouble()) {
                throw UserInputException.INVALID_AMOUNT;
            }
            financeCommand = new FinanceCommand(CommandType.ADD, name, scanner.nextDouble());
            break;
        case REDUCE:
            if (!scanner.hasNextDouble()) {
                throw UserInputException.INVALID_AMOUNT;
            }
            financeCommand = new FinanceCommand(CommandType.REDUCE, name, scanner.nextDouble());
            break;
        case RENAME:
            if (!scanner.hasNext()) {
                throw UserInputException.EMPTY_CATEGORY;
            }
            financeCommand = new FinanceCommand(CommandType.RENAME, name, scanner.next());
            break;
        default:
            throw UserInputException.INVALID_COMMAND;
        }
        return financeCommand;
    }

    /**
     * Parses a specified line from a file and converts it into a task.
     *
     * @param line The line to be parsed.
     * @return The task parsed from the line.
     * @throws FileException If the line deviates from any of the acceptable formats
     */
    public static Task parseTaskFromFile(String line) throws FileException {
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
            throw FileException.FILE_PARSING_FAILURE;
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
            throw FileException.FILE_PARSING_FAILURE;
        }
        if (isDoneInteger == 1) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses a specified line from a file and converts it into a category.
     *
     * @param line The line to be parsed.
     * @return The category parsed from the line.
     * @throws FileException If the line from the file is incorrectly formatted.
     */
    public static Category parseCategoryFromFile(String line) throws FileException, UserInputException {
        assert (!line.isEmpty());
        Scanner parsingScanner = new Scanner(line);
        String name = parsingScanner.next();
        Category category = new Category(name);
        try {
            parsingScanner.next();
            category.addAmount(parsingScanner.nextDouble());
            return category;
        } catch (NoSuchElementException e) {
            throw FileException.FILE_PARSING_FAILURE;
        }
    }
}
