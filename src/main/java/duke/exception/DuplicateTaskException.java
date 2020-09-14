package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when a new {@code Task} to be added already exists in the current {@code TaskList}.
 */
public class DuplicateTaskException extends DukeException {

    private static final String DUPLICATE_TASK_MESSAGE = "This task already exists in the list!";

    /**
     * Initializes the DuplicateTaskException.
     */
    public DuplicateTaskException() {
        super(Ui.stringFormatter(DUPLICATE_TASK_MESSAGE));
    }
}
