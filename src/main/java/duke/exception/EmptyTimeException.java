package duke.exception;

import duke.task.TaskType;

/**
 * Thrown when the Complex Task does not have its time specified.
 */
public class EmptyTimeException extends DukeException {
    /**
     * Initializes the EmptyTimeException object.
     *
     * @param complexTask Complex Task.
     */
    public EmptyTimeException(TaskType complexTask) {
        super(String.format("Deadline / time of %s is not specified", complexTask.toString().toLowerCase()));
    }
}