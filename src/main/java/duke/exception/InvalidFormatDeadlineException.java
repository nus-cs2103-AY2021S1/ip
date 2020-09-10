package duke.exception;

import static duke.util.Keyword.KEYWORD_DEADLINE_EXCEPTION;

public class InvalidFormatDeadlineException extends DukeException {
    public InvalidFormatDeadlineException() {
        super(KEYWORD_DEADLINE_EXCEPTION);
    }
}
