package duke.exception;

import static duke.util.Keyword.KEYWORD_REMINDER_EXCEPTION;

/**
 * Thrown when user key in an invalid reminder format.
 */
public class InvalidFormatReminderException extends DukeException {

    /**
     * Initialize the InvalidFormatReminderException Object.
     */
    public InvalidFormatReminderException() {
        super(KEYWORD_REMINDER_EXCEPTION);
    }
}
