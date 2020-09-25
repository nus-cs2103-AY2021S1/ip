package duke.exception;

import static duke.util.Keyword.KEYWORD_BYE_EXCEPTION;

/**
 * Thrown when the user type an invalid bye format.
 */
public class InvalidFormatByeException extends DukeException {

    /**
     * Initialize the InvalidFormatByeException Object.
     */
    public InvalidFormatByeException() {
        super(KEYWORD_BYE_EXCEPTION);
    }
}
