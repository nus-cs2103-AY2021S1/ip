package duke.exception;

import static duke.util.Keyword.KEYWORD_HELP_EXCEPTION;

/**
 * Thrown when user key in an invalid help format.
 */
public class InvalidFormatHelpException extends DukeException {

    /**
     * Initialize the InvalidFormatHelpException Object.
     */
    public InvalidFormatHelpException() {
        super(KEYWORD_HELP_EXCEPTION);
    }
}
