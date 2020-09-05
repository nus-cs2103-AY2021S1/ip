package duke.parser;

import duke.command.AddComplexTaskCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ShowCommand;
import duke.command.SimpleCommand;
import duke.command.SimpleCommandType;
import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.UnknownCommandException;
import duke.task.TaskType;

/**
 * Parses the user input and creating a command from it.
 */
public class Parser {

    private static final String INVALID_ARR_ERROR = "Array is not empty";

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param text User text input.
     * @return Corresponding command.
     * @throws DukeException If user input does not belong to the list of known commands.
     */
    public static Command parse(String text) throws DukeException {
        String[] inputArr = deconstructInput(text);
        String keyWord = getKeyWord(inputArr);
        String details = getRestOfWord(inputArr);

        if (keyWord.equals("list")) {
            return new ShowCommand();
        } else if (keyWord.equals("done")) {
            return new SimpleCommand(details, SimpleCommandType.DONE);
        } else if (keyWord.equals("delete")) {
            return new SimpleCommand(details, SimpleCommandType.DELETE);
        } else if (keyWord.equals("todo")) {
            return new AddToDoCommand(details);
        } else if (keyWord.equals("deadline")) {
            return new AddComplexTaskCommand(details, TaskType.DEADLINE);
        } else if (keyWord.equals("event")) {
            return new AddComplexTaskCommand(details, TaskType.EVENT);
        } else if (keyWord.equals("find")) {
            return new FindCommand(details);
        } else if (keyWord.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Deconstructs the string into a string array.
     *
     * @param input Input String.
     * @return String array of the input.
     */
    private static String[] deconstructInput(String input) {
        String formattedString = input.trim().replaceAll("\\s{2,}", " ");
        return formattedString.split(" ", 2);
    }

    /**
     * Obtains the keyword of the user input.
     *
     * @param arr User input in a String array.
     * @return Keyword.
     */
    private static String getKeyWord(String[] arr) {
        assert (arr.length > 0) : INVALID_ARR_ERROR;
        return arr[0].toLowerCase();
    }

    /**
     * Obtains the rest of the user input.
     *
     * @param arr User input in a String array.
     * @return Rest of the user input.
     */
    private static String getRestOfWord(String[] arr) {
        assert (arr.length > 0) : INVALID_ARR_ERROR;
        return arr.length == 1 ? "" : arr[1];
    }

    /**
     * Returns the unique identifier tied to the ComplexTask.
     *
     * @return String identifier of the Task.
     */
    private static String getIdentifier(TaskType taskType) {
        assert (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT);
        return taskType == TaskType.DEADLINE ? " /by" : " /at";
    }

    /**
     * Parses the description for ComplexTask.
     *
     * @param description Description of Complex task.
     * @param taskType Task type of Complex task.
     * @return String Array consisting of task details and time.
     * @throws DukeException If the format inputted is not correct.
     */
    public static String[] parseComplexTaskDescription(String description, TaskType taskType) throws DukeException {
        String[] inputArr = description.split(getIdentifier(taskType), 2);

        if (inputArr.length == 1) {
            if (taskType == TaskType.DEADLINE) {
                throw new InvalidDeadlineException();
            }
            if (taskType == TaskType.EVENT) {
                throw new InvalidEventException();
            }
        }

        String taskDetails = inputArr[0];
        String time = inputArr[1];

        if (taskDetails.isEmpty()) {
            throw new EmptyTaskException(taskType);
        }
        if (time.isBlank()) {
            throw new EmptyTimeException(taskType);
        }

        inputArr[1] = inputArr[1].trim();
        return inputArr;
    }
}
