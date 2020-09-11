package duke.exception;

import static duke.util.Keyword.KEYWORD_DEADLINE_EXCEPTION;

/**
 * Thrown when user key in an invalid deadline format.
 */
public class InvalidFormatDeadlineException extends DukeException {
    /**
     * Initializes the InvalidFormatDeadlineException Object.
     */
    public InvalidFormatDeadlineException() {
        super(KEYWORD_DEADLINE_EXCEPTION);
    }
}
