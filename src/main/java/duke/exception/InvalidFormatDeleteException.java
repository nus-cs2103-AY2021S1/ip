package duke.exception;

import static duke.util.Keyword.KEYWORD_DELETE_EXCEPTION;

/**
 * Thrown when user key in an invalid delete format.
 */
public class InvalidFormatDeleteException extends DukeException {

    /**
     * Initialize the InvalidFormatDeleteException Object.
     */
    public InvalidFormatDeleteException() {
        super(KEYWORD_DELETE_EXCEPTION);
    }
}
