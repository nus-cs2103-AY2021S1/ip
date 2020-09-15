package duke.exception;

import static duke.util.Keyword.TASK_ALREADY_DONE_MESSAGE;

import duke.ui.Ui;

/**
 * Thrown when the task is already done.
 */
public class TaskAlreadyDoneException extends DukeException {

    /**
     * Initializes the TaskAlreadyDoneException object.
     */
    public TaskAlreadyDoneException() {
        super(Ui.stringFormatter(TASK_ALREADY_DONE_MESSAGE));
    }
}
