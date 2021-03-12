package duke.exception;

import static duke.util.Keyword.EMPTY_TASK_DESCRIPTION_MESSAGE;

import duke.ui.Ui;

/**
 * Thrown when the task detail of a Task object is empty.
 */
public class EmptyToDoException extends DukeException {

    /**
     * Initializes the EmptyToDoException object.
     */
    public EmptyToDoException() {
        super(Ui.stringFormatter(EMPTY_TASK_DESCRIPTION_MESSAGE));
    }
}
