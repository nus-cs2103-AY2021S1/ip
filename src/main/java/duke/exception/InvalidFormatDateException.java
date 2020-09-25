package duke.exception;

import static duke.util.Keyword.KEYWORD_DATE_EXCEPTION;

/**
 * Thrown when user key in an invalid date format.
 */
public class InvalidFormatDateException extends DukeException {

    /**
     * Initialize the InvalidFormatDateException Object.
     */
    public InvalidFormatDateException() {
        super(KEYWORD_DATE_EXCEPTION);
    }
}
