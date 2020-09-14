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
     * @throws NoTaskContentException  If user inputs a add task command without a description and/or date/time.
     * @throws NoTaskDateTimeException  If user inputs a deadline command without date/time.
     * @throws NoTaskDurationException  If user inputs an event command without date/time.
     * @throws InvalidCommandException  If user inputs an unknown command.
     * @throws InvalidDeleteException  If user inputs a delete command without specifying a number.
     * @throws InvalidTaskDateException  If user input an incorrect date and/or time format.
     * @throws NoFindContentException  If user input does not contain any keywords to find.

     */
    public static Command parse(String input, TaskList tasks) throws NoTaskException,
            InvalidDoneException, InvalidTaskNumberException, NoTaskContentException,
            NoTaskDateTimeException, NoTaskDurationException, InvalidCommandException,
            InvalidDeleteException, InvalidTaskDateException, NoFindContentException {
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
     * Returns true if an taskNumber id between 0 and the number of Task in tasks.
     * This method is to check that a number input by user is valid.
     *
     * @param taskNumber Number to be checked.
     * @param tasks A Task List of containing Task.
     * @return boolean to indicate if the number is between 0 and the number of Task in tasks.
     */
    private static boolean checkForValidTaskNumber(int taskNumber, TaskList tasks) {
        return taskNumber >= 0 || taskNumber < tasks.getNumberOfTask();
    }

    /**
     * Returns the ListCommand if user input is valid.
     *
     * @param tasks TasksList use by Duke to store the tasks.
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
     * @param tasks TasksList use by Duke to store the tasks.
     * @return DoneCommand.
     * @throws InvalidDoneException  If user inputs a done command without specifying a number.
     * @throws InvalidTaskNumberException  If user inputs a number that is not valid.
     */
    private static DoneCommand parseDone(String input, TaskList tasks) throws InvalidDoneException,
            InvalidTaskNumberException {
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
     * Returns commands to add Task if user input is valid.
     *
     * @param input User input to Duke.
     * @return command that add Tasks
     * @throws NoTaskContentException  If user inputs a add task command without a description and/or date/time.
     * @throws NoTaskDateTimeException  If user inputs a deadline command without date/time.
     * @throws NoTaskDurationException  If user inputs an event command without date/time.
     * @throws InvalidCommandException  If user inputs an unknown command.
     */
    private static Command parseAddTask(String input) throws NoTaskContentException,
            NoTaskDateTimeException, NoTaskDurationException, InvalidCommandException {
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

    /**
     * Returns the AddTodoCommand if user input is valid.
     *
     * @param splitInput User input that is split up by a ' '.
     * @return AddTodoCommand.
     * @throws NoTaskContentException  If user inputs a todo command without a description.
     */
    private static AddTodoCommand parseAddTodo(String[] splitInput) throws NoTaskContentException {
        boolean hasContent = checkForContent(splitInput);
        if (!hasContent) {
            throw new NoTaskContentException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = splitInput[1];
        return new AddTodoCommand(description);
    }

    /**
     * Returns the AddDeadlineCommand if user input is valid.
     *
     * @param splitInput User input that is split up by a ' '.
     * @return AddDeadlineCommand.
     * @throws NoTaskContentException  If user inputs a deadline command without a description or date/time.
     * @throws NoTaskDateTimeException  If user inputs a deadline command without date/time.
     */
    private static AddDeadlineCommand parseAddDeadline(String[] splitInput) throws NoTaskContentException,
            NoTaskDateTimeException {
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

    /**
     * Returns the AddEventCommand if user input is valid.
     *
     * @param splitInput User input that is split up by a ' '.
     * @return AddEventCommand.
     * @throws NoTaskContentException  If user inputs an event command without a description or date/time.
     * @throws NoTaskDurationException  If user inputs an event command without date/time.
     */
    private static AddEventCommand parseAddEvent(String[] splitInput) throws NoTaskContentException,
            NoTaskDurationException {
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
     * @throws InvalidDeleteException  If user inputs a delete command without specifying a number.
     * @throws InvalidTaskNumberException  If user inputs a number that is not valid.
     */
    private static DeleteCommand parseDelete(String input, TaskList tasks) throws InvalidDeleteException,
            InvalidTaskNumberException {
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
     * @throws NoTaskDateTimeException  If user input does not contain date and/or time.
     * @throws InvalidTaskDateException  If user input an incorrect date and/or time format.
     */
    private static RetrieveCommand parseRetrieve(String input) throws NoTaskDateTimeException,
            InvalidTaskDateException {
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
     * @throws NoFindContentException  If user input does not contain any keywords to find.
     */
    private static FindCommand parseFind(String input) throws NoFindContentException {
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
