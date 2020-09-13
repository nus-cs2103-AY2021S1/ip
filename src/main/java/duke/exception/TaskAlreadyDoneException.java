package duke.exception;

/**
 * Thrown when the task is already done.
 */
public class TaskAlreadyDoneException extends DukeException {

    private static final String TASK_ALREADY_DONE_MESSAGE = "Task has already been mark as done!";

    /**
     * Initializes the TaskAlreadyDoneException object.
     */
    public TaskAlreadyDoneException() {
        super(TASK_ALREADY_DONE_MESSAGE);
    }
}