package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the task detail of a Task object is empty.
 */
public class EmptyToDoException extends DukeException {

    private static final String EMPTY_TASK_DESCRIPTION_MESSAGE = "The description of a todo cannot be empty.";

    /**
     * Initializes the EmptyToDoException object.
     */
    public EmptyToDoException() {
        super(Ui.stringFormatter(EMPTY_TASK_DESCRIPTION_MESSAGE));
    }
}
