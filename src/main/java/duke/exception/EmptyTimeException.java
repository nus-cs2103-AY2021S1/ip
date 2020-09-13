package duke.exception;

import duke.task.TaskType;

/**
 * Thrown when either a {@code Deadline} or {@code Event} Task does not have its time specified.
 */
public class EmptyTimeException extends DukeException {

    private static final String EMPTY_TIME_MESSAGE = "Time of %s task is not specified";

    /**
     * Initializes the {@code EmptyTimeException} object.
     *
     * @param taskType Type of {@code Task}.
     */
    public EmptyTimeException(TaskType taskType) {
        super(getEmptyTimeMessage(taskType));
    }

    public static String getEmptyTimeMessage(TaskType taskType) {
        return String.format(EMPTY_TIME_MESSAGE, taskType.toLower());
    }
}
