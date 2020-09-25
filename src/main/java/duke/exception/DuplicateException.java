package duke.exception;

import static duke.util.Keyword.KEYWORD_DUPLICATE_EXCEPTION;

/**
 * Thrown when user is trying to add a task when it exist in the task list.
 */
public class DuplicateException extends DukeException {

    /**
     * Initialize the DuplicateException Object.
     */
    public DuplicateException() {
        super(KEYWORD_DUPLICATE_EXCEPTION);
    }
}
