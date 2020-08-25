package duke.exception;

import duke.task.TaskType;

/**
 * Thrown when the Complex Task does not have its time specified.
 */
public class EmptyByException extends DukeException {
    /**
     * Initializes the EmptyByException object.
     *
     * @param complexTask Complex Task.
     */
    public EmptyByException(TaskType complexTask) {
        super(String.format("Deadline / time of %s is not specified", complexTask));
    }
}