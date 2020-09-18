package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.RetrieveCommand;
import duke.command.SortCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeleteException;
import duke.exception.InvalidDoneException;
import duke.exception.InvalidTaskContentException;
import duke.exception.InvalidTaskDateException;
import duke.exception.InvalidTaskDateTimeException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NoFindContentException;
import duke.exception.NoTaskContentException;
import duke.exception.NoTaskDateException;
import duke.exception.NoTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * The <code>Parser</code> class make sense of the user inputs.
 * A Parser object decides which commands to call or exceptions to throw.
 */
public class Parser {

    /**
     * Returns the commands to call based on user inputs.
     *
     * @param input User input to Duke.
     * @param tasks TasksList use by Duke to store the Task.
     * @return A command to call.
     * @throws NoTaskException  If there is no Task in the TaskList.
     * @throws InvalidDoneException  If user inputs a done command without specifying a number.
     * @throws InvalidTaskNumberException  If user inputs a number that is not valid.
     * @throws NoTaskContentException  If user inputs an add task command without a description and/or date/time.
     * @throws NoTaskDateException  If user inputs an retrieve command without date.
     * @throws InvalidTaskDateTimeException If user inputs a add deadline or add event command
     * with a wrong date/time format.
     * @throws InvalidCommandException  If user inputs an unknown command.
     * @throws InvalidDeleteException  If user inputs a delete command without specifying a number.
     * @throws InvalidTaskDateException  If user input an incorrect date and/or time format.
     * @throws InvalidTaskContentException If user input an add Task command with an invalid format.
     * @throws NoFindContentException  If user input does not contain any keywords to find.
     */
    public static Command parse(String input, TaskList tasks) throws NoTaskException,
            InvalidDoneException, InvalidTaskNumberException, NoTaskContentException,
            NoTaskDateException, InvalidTaskDateTimeException, InvalidCommandException,
            InvalidDeleteException, InvalidTaskDateException, InvalidTaskContentException,
            NoFindContentException {
        String trimmedInput = input.trim();
        if (trimmedInput.equals("bye")) {
            return new ByeCommand();
        } else if (trimmedInput.equals("list")) {
            return parseList(tasks);
        } else if (trimmedInput.startsWith("done")) {
            return parseDone(trimmedInput, tasks);
        } else if (trimmedInput.startsWith("todo")
                || trimmedInput.startsWith("deadline")
                || trimmedInput.startsWith("event")) {
            return parseAddTask(trimmedInput);
        } else if (trimmedInput.startsWith("delete")) {
            return parseDelete(trimmedInput, tasks);
        } else if (trimmedInput.startsWith("retrieve")) {
            return parseRetrieve(trimmedInput);
        } else if (trimmedInput.startsWith("find")) {
            return parseFind(trimmedInput);
        } else if (trimmedInput.equals("sort")) {
            return parseSort(trimmedInput, tasks);
        } else if (trimmedInput.equals("help")) {
            return new HelpCommand();
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns true if an array has length of 2.
     * This method is to check that a command input by user has content.
     *
     * @param splitCommand An array of String which belongs to a command.
     * @return boolean to indicate if a command consist of any content.
     */
    private static boolean checkForContent(String[] splitCommand) {
        return splitCommand.length == 2;
    }

    /**
     * Returns true if an taskNumber is between 0 and the number of Task in tasks.
     * This method is to check that a number input by user is valid.
     *
     * @param taskNumber Number to be checked.
     * @param tasks A TaskList of containing Task.
     * @return boolean to indicate if the number is between 0 and the number of Task in tasks.
     */
    private static boolean checkForValidTaskNumber(int taskNumber, TaskList tasks) {
        return taskNumber >= 0 && taskNumber < tasks.getNumberOfTask();
    }

    /**
     * Returns the ListCommand if user input is valid.
     *
     * @param tasks TasksList use by Duke to store the Task.
     * @return ListCommand.
     * @throws NoTaskException  If there is no Task in the TaskList.
     */
    private static ListCommand parseList(TaskList tasks) throws NoTaskException {
        boolean hasTasks = tasks.getNumberOfTask() > 0;

        if (!hasTasks) {
            throw new NoTaskException();
        }
        return new ListCommand();
    }

    /**
     * Returns the DoneCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @param tasks TasksList use by Duke to store the Task.
     * @return DoneCommand.
     * @throws InvalidDoneException  If user inputs a done command without specifying a number.
     * @throws InvalidTaskNumberException  If user inputs a number that is not valid.
     */
    private static DoneCommand parseDone(String input, TaskList tasks) throws InvalidDoneException,
            InvalidTaskNumberException {
        try {
            assert input.startsWith("done") : "input should start with done";
            String[] splitInput = input.split(" ");
            boolean hasContent = checkForContent(splitInput);

            if (!hasContent || !splitInput[1].matches("-?[0-9]+")) {
                throw new InvalidDoneException();
            }

            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
            boolean isValidTaskNumber = checkForValidTaskNumber(taskNumber, tasks);
            if (!isValidTaskNumber) {
                throw new InvalidTaskNumberException("OOPS!!! No such task available.");
            }
            return new DoneCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("OOPS!!! Task Number is invalid.");
        }
    }

    /**
     * Returns AddTaskCommand to add Task if user input is valid.
     *
     * @param input User input to Duke.
     * @return AddTaskCommand.
     * @throws NoTaskContentException  If user inputs a add task command without a description and/or date/time.
     * @throws InvalidTaskContentException  If user inputs an add deadline or add event command.
     * without description and/or date/time.
     * @throws InvalidTaskDateTimeException If user inputs a add deadline or add event command.
     * with a wrong date/time format.
     * @throws InvalidCommandException  If user inputs an unknown command.
     */
    private static AddTaskCommand parseAddTask(String input) throws NoTaskContentException,
            InvalidTaskContentException, InvalidTaskDateTimeException, InvalidCommandException {
        String[] splitInput = input.split(" ", 2);
        String taskWord = splitInput[0];
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent) {
            throw new NoTaskContentException();
        }
        String content = splitInput[1];
        switch(taskWord) {
        case "todo":
            return new AddTaskCommand(new Todo(content));
        case "deadline":
            return parseAddDeadline(content);
        case "event":
            return parseAddEvent(content);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns the AddTaskCommand if user input is valid.
     *
     * @param content User input after the task type.
     * @return AddTaskCommand.
     * @throws InvalidTaskContentException  If user inputs an deadline command without description and/or date/time.
     * @throws InvalidTaskDateTimeException  If user input an add deadline command with a wrong date/time format.
     */
    private static AddTaskCommand parseAddDeadline(String content) throws InvalidTaskDateTimeException,
            InvalidTaskContentException {
        try {
            String[] splitContent = content.split(" /by ", 2);
            boolean hasValidContent = checkForContent(splitContent);
            if (!hasValidContent) {
                assert splitContent.length == 1 : "size of splitContent must be one";
                throw new InvalidTaskContentException(
                        "OOPS!!! Enter \"deadline <description> /by <dd/MM/yyyy HH:mm>\" to add a new deadline!");
            }
            String description = splitContent[0];
            String by = splitContent[1];
            return new AddTaskCommand(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeException();
        }
    }

    /**
     * Returns the AddTaskCommand if user input is valid.
     *
     * @param content User input after the task type.
     * @return AddTaskCommand.
     * @throws InvalidTaskContentException  If user inputs an add event command without description and/or date/time.
     * @throws InvalidTaskDateTimeException  If user input an add event command with a wrong date/time format.
     */
    private static AddTaskCommand parseAddEvent(String content) throws InvalidTaskContentException,
            InvalidTaskDateTimeException {
        try {
            String[] splitContent = content.split(" /at ", 2);
            boolean hasValidContent = checkForContent(splitContent);
            if (!hasValidContent) {
                assert splitContent.length == 1 : "size of splitContent must be one";
                throw new InvalidTaskContentException(
                        "OOPS!!! Enter \"event <description> /at <dd/MM/yyyy HH:mm>\" to add a new event!");
            }
            String description = splitContent[0];
            String at = splitContent[1];
            return new AddTaskCommand(new Event(description, at));
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeException();
        }
    }

    /**
     * Returns the DeleteCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @param tasks TasksList use by Duke to store the tasks.
     * @return DeleteCommand.
     * @throws InvalidDeleteException  If user inputs a delete command without specifying a number.
     * @throws InvalidTaskNumberException  If user inputs a number that is not valid.
     */
    private static DeleteCommand parseDelete(String input, TaskList tasks) throws InvalidDeleteException,
            InvalidTaskNumberException {
        try {
            assert input.startsWith("delete") : "input should start with delete";

            String[] splitInput = input.split(" ");
            boolean hasContent = checkForContent(splitInput);
            if (!hasContent || !splitInput[1].matches("-?[0-9]+")) {
                throw new InvalidDeleteException();
            }

            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
            boolean isValidTaskNumber = checkForValidTaskNumber(taskNumber, tasks);
            if (!isValidTaskNumber) {
                throw new InvalidTaskNumberException("OOPS!!! No such task available.");
            }
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("OOPS!!! Task Number is invalid.");
        }
    }

    /**
     * Returns the RetrieveCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @return RetrieveCommand.
     * @throws NoTaskDateException  If user input does not contain date.
     * @throws InvalidTaskDateException  If user input an incorrect date format.
     */
    private static RetrieveCommand parseRetrieve(String input) throws NoTaskDateException,
            InvalidTaskDateException {
        assert input.startsWith("retrieve") : "input should start with retrieve";

        try {
            String[] splitInput = input.split(" ");
            boolean hasContent = checkForContent(splitInput);
            if (!hasContent) {
                throw new NoTaskDateException();
            }
            LocalDate date = LocalDate.parse(splitInput[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new RetrieveCommand(date);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }
    }

    /**
     * Returns the FindCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @return FindCommand.
     * @throws NoFindContentException  If user input does not contain any keywords to find.
     */
    private static FindCommand parseFind(String input) throws NoFindContentException {
        assert input.startsWith("find") : "input should start with find";
        String[] splitInput = input.split(" ", 2);
        boolean hasContent = checkForContent(splitInput);

        if (!hasContent) {
            throw new NoFindContentException();
        }
        String content = splitInput[1];
        return new FindCommand(content);
    }

    /**
     * Returns the SortCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @param tasks TaskList containing Task to sort.
     * @return SortCommand.
     * @throws NoTaskException  If there is no Task in the TaskList to sort.
     */
    private static SortCommand parseSort(String input, TaskList tasks) throws NoTaskException {
        assert input.startsWith("sort") : "input should start with sort";
        boolean hasTasks = tasks.getNumberOfTask() > 0;

        if (!hasTasks) {
            throw new NoTaskException();
        }
        return new SortCommand();
    }
}
