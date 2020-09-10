package duke.exception;

import static duke.util.Keyword.KEYWORD_REMINDER_EXCEPTION;

public class InvalidFormatReminderException extends DukeException {
    public InvalidFormatReminderException() {
        super(KEYWORD_REMINDER_EXCEPTION);
    }
}
