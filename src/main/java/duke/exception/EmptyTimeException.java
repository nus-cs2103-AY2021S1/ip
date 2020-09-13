package duke.exception;

import duke.task.TaskType;

/**
 * Thrown when either a {@code Deadline} or {@code Event} Task does not have its time specified.
 */
public class EmptyTimeException extends DukeException {

    /**
     * Initializes the {@code EmptyTimeException} object.
     *
     * @param taskType Type of {@code Task}.
     */
    public EmptyTimeException(TaskType taskType) {
        super(String.format("Time of %s task is not specified", taskType.toString().toLowerCase()));
    }
}
