package duke.exception;

/**
 * Thrown when the task is already done.
 */
public class TaskAlreadyDoneException extends DukeException {

    /**
     * Initializes the TaskAlreadyDoneException object.
     */
    public TaskAlreadyDoneException() {
        super("Task has already been mark as done!");
    }
}
