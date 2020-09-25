package duke.exception;

import static duke.util.Keyword.KEYWORD_DONE_EXCEPTION;

/**
 * Thrown when user key in an invalid done format.
 */
public class InvalidFormatDoneException extends DukeException {

    /**
     * Initialize the InvalidFormatDoneException Object.
     */
    public InvalidFormatDoneException() {
        super(KEYWORD_DONE_EXCEPTION);
    }
}
