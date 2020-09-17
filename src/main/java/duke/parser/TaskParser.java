package duke.parser;

import static duke.util.Keyword.ARRAY_SIZE;
import static duke.util.Keyword.DEADLINE_DELIMITER;
import static duke.util.Keyword.EVENT_DELIMITER;
import static duke.util.Keyword.INVALID_TASK_TYPE;

import duke.exception.EmptyTimeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.task.TaskType;

/**
 * Handles the parsing of the {@code Event} and {@code Deadline} task descriptions (String after keyword).
 */
public class TaskParser {

    /**
     * Returns the unique identifier tied to this {@code Task}.
     *
     * @param taskType Task type of task.
     * @return String identifier of the Task.
     */
    private static String getIdentifier(TaskType taskType) {
        assert (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT);
        return taskType == TaskType.EVENT ? EVENT_DELIMITER : DEADLINE_DELIMITER;
    }

    /**
     * Throws an {@code Exception} specific to the {@code TaskType}.
     *
     * @param taskType Task type of task.
     * @throws InvalidDeadlineException If deadline is not formatted correctly.
     * @throws InvalidEventException If Event is not formatted correctly.
     */
    private static void throwException(TaskType taskType) throws InvalidDeadlineException, InvalidEventException {
        switch (taskType) {
        case DEADLINE:
            throw new InvalidDeadlineException();
        case EVENT:
            throw new InvalidEventException();
        default:
            assert false : INVALID_TASK_TYPE;
        }
    }

    /**
     * Deconstructs the user input.
     *
     * @param description Remaining string after keyword.
     * @param taskType TaskType of task.
     * @return String array of the input.
     */
    private static String[] deconstructInput(String description, TaskType taskType) {
        String identifier = getIdentifier(taskType);
        return description.split(identifier, ARRAY_SIZE);
    }

    /**
     * Parses the description for the {@code Deadline} and {@code Event} tasks and returns an array representation
     * of the input.
     *
     * @param description Description of task.
     * @param taskType Task type of task.
     * @return String array consisting of task details and time.
     * @throws InvalidDeadlineException If deadline is not formatted correctly.
     * @throws InvalidEventException If Event is not formatted correctly.
     * @throws EmptyTimeException If time of event is not specified.
     */
    public static String[] parseTaskDescription(String description, TaskType taskType)
            throws InvalidDeadlineException, InvalidEventException, EmptyTimeException {

        String[] inputArr = deconstructInput(description, taskType);
        if (inputArr.length < ARRAY_SIZE) {
            throwException(taskType);
        }
        String time = inputArr[1];
        if (time.isBlank()) {
            throw new EmptyTimeException(taskType);
        }
        inputArr[1] = inputArr[1].trim();
        return inputArr;
    }

    /**
     * Retrieves the task details of a task from a parsed String array.
     *
     * @param arr Parsed String array.
     * @return Task details of task.
     */
    public static String getTaskDetails(String[] arr) {
        return arr[0];
    }

    /**
     * Retrieves the time of the task from a parsed String array, time frame for event and due date for deadline.
     *
     * @param arr Parsed String array.
     * @return Time of task.
     */
    public static String getTime(String[] arr) {
        return arr[1];
    }
}
