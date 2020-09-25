package duke.exception;

import static duke.util.Keyword.KEYWORD_EVENT_EXCEPTION;

/**
 * Thrown when user key in an invalid event format.
 */
public class InvalidFormatEventException extends DukeException {

    /**
     * Initialize the InvalidFormatEventException Object.
     */
    public InvalidFormatEventException() {
        super(KEYWORD_EVENT_EXCEPTION);
    }
}
