package duke.exception;

import static duke.util.Keyword.DUPLICATE_TASK_MESSAGE;

import duke.ui.Ui;

/**
 * Thrown when a new {@code Task} to be added already exists in the current {@code TaskList}.
 */
public class DuplicateTaskException extends DukeException {

    /**
     * Initializes the DuplicateTaskException.
     */
    public DuplicateTaskException() {
        super(Ui.stringFormatter(DUPLICATE_TASK_MESSAGE));
    }
}
