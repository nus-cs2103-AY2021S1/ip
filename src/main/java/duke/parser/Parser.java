package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RetrieveCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeleteException;
import duke.exception.InvalidDoneException;
import duke.exception.InvalidTaskDateException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NoFindContentException;
import duke.exception.NoTaskContentException;
import duke.exception.NoTaskDateTimeException;
import duke.exception.NoTaskDurationException;
import duke.exception.NoTaskException;
import duke.tasklist.TaskList;

/**
 * The <code>Parser</code> class make sense of the
 * user inputs. Decides which command to call or exception to throw.
 */
public class Parser {

    /**
     * Returns the commands to call based on user inputs.
     *
     * @param input User input to Duke.
     * @param tasks TasksList use by Duke to store the tasks.
     * @return A command to call.
     * @throws DukeException If user input is not valid.
     */
    public static Command parse(String input, TaskList tasks) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return parseList(tasks);
        } else if (input.startsWith("done")) {
            return parseDone(input, tasks);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return parseAddTask(input);
        } else if (input.startsWith("delete")) {
            return parseDelete(input, tasks);
        } else if (input.startsWith("retrieve")) {
            return parseRetrieve(input);
        } else if (input.startsWith("find")) {
            return parseFind(input);
        } else if (input.equals("sort")) {
            return parseSort(input, tasks);
        } else {
            throw new InvalidCommandException();
        }
    }

    private static boolean checkForContent(String[] splitCommand) {
        return splitCommand.length == 2;
    }

    private static boolean checkForValidTaskNumber(int taskNumber, TaskList tasks) {
        return taskNumber >= 0 || taskNumber < tasks.getNumberOfTask();
    }

    /**
     * Returns the ListCommand if user input is valid.
     *
     * @param tasks TasksList use by Duke to store the tasks.
     * @return ListCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseList(TaskList tasks) throws DukeException {
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
     * @param tasks TasksList use by Duke to store the tasks.
     * @return DoneCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseDone(String input, TaskList tasks) throws DukeException {
        assert input.startsWith("done") : "input should start with done";
        String[] splitInput = input.split(" ");
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent || !splitInput[1].matches("[0-9]+")) {
            throw new InvalidDoneException();
        }
        int taskNumber = Integer.parseInt(splitInput[1]) - 1;
        boolean isValidTaskNumber = checkForValidTaskNumber(taskNumber, tasks);
        if (!isValidTaskNumber) {
            throw new InvalidTaskNumberException();
        }
        return new DoneCommand(taskNumber);
    }

    /**
     * Returns commands to add tasks if user input is valid.
     *
     * @param input User input to Duke.
     * @return commands that add tasks
     * @throws DukeException If user input is not valid.
     */
    public static Command parseAddTask(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        String taskWord = splitInput[0];
        switch(taskWord) {
        case "todo":
            return parseAddTodo(splitInput);
        case "deadline":
            return parseAddDeadline(splitInput);
        case "event":
            return parseAddEvent(splitInput);
        default:
            throw new InvalidCommandException();
        }
    }

    private static Command parseAddTodo(String[] splitInput) throws DukeException {
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent) {
            throw new NoTaskContentException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = splitInput[1];
        return new AddTodoCommand(description);
    }

    private static Command parseAddDeadline(String[] splitInput) throws DukeException {
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent) {
            throw new NoTaskContentException(
                    "OOPS!!! The description and date/time of a deadline cannot be empty.");
        }
        String content = splitInput[1];
        String[] splitContent = content.split(" /by ", 2);
        boolean hasDateTime = checkForContent(splitContent);
        if (!hasDateTime) {
            throw new NoTaskDateTimeException(
                    "OOPS!!! The date/time of a deadline cannot be empty.");
        }
        String description = splitContent[0];
        String by = splitContent[1];
        return new AddDeadlineCommand(description, by);
    }

    private static Command parseAddEvent(String[] splitInput) throws DukeException {
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent) {
            throw new NoTaskContentException(
                    "OOPS!!! The description and duration of an event cannot be empty.");
        }
        String content = splitInput[1];
        String[] splitContent = content.split(" /at ", 2);
        boolean hasDateTime = checkForContent(splitContent);
        if (!hasDateTime) {
            throw new NoTaskDurationException(
                    "OOPS!!! The duration of an event cannot be empty.");
        }
        String description = splitContent[0];
        String at = splitContent[1];
        return new AddEventCommand(description, at);
    }

    /**
     * Returns the DeleteCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @param tasks TasksList use by Duke to store the tasks.
     * @return DeleteCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseDelete(String input, TaskList tasks) throws DukeException {
        assert input.startsWith("delete") : "input should start with delete";
        String[] splitInput = input.split(" ");
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent || !splitInput[1].matches("[0-9]+")) {
            throw new InvalidDeleteException();
        }
        int taskNumber = Integer.parseInt(splitInput[1]) - 1;
        boolean isValidTaskNumber = checkForValidTaskNumber(taskNumber, tasks);
        if (!isValidTaskNumber) {
            throw new InvalidTaskNumberException();
        }
        return new DeleteCommand(taskNumber);
    }

    /**
     * Returns the RetrieveCommand if user input is valid.
     *
     * @param input User input to Duke.
     * @return RetrieveCommand.
     * @throws DukeException If user input is not valid.
     */
    public static Command parseRetrieve(String input) throws DukeException {
        assert input.startsWith("retrieve") : "input should start with retrieve";
        try {
            String[] splitInput = input.split(" ");
            boolean hasContent = checkForContent(splitInput);
            if (!hasContent) {
                throw new NoTaskDateTimeException("OOPS!!! Please enter a date.");
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
     * @throws DukeException If user input is not valid.
     */
    private static Command parseFind(String input) throws DukeException {
        assert input.startsWith("find") : "input should start with find";
        String[] splitInput = input.split(" ", 2);
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent || splitInput[1].trim().equals("")) {
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
     * @throws NoTaskException If there is no Task in the TaskList to sort.
     */
    private static Command parseSort(String input, TaskList tasks) throws NoTaskException {
        assert input.startsWith("sort") : "input should start with sort";
        boolean hasTasks = tasks.getNumberOfTask() > 0;

        if (!hasTasks) {
            throw new NoTaskException();
        }
        return new SortCommand();
    }
}

