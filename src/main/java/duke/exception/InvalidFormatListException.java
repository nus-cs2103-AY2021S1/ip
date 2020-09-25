package duke.exception;

import static duke.util.Keyword.KEYWORD_LIST_EXCEPTION;

/**
 * Thrown when user key in an invalid list format.
 */
public class InvalidFormatListException extends DukeException {

    /**
     * Initialize the InvalidFormatListException Object.
     */
    public InvalidFormatListException() {
        super(KEYWORD_LIST_EXCEPTION);
    }
}
