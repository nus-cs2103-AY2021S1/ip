package duke.exception;

import static duke.util.Keyword.KEYWORD_FIND_EXCEPTION;

/**
 * Thrown when user key in an invalid find format.
 */
public class InvalidFormatFindException extends DukeException {

    /**
     * Initialize the InvalidFormatFindException Object.
     */
    public InvalidFormatFindException() {
        super(KEYWORD_FIND_EXCEPTION);
    }
}
