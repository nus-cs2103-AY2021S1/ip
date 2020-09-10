package duke.exception;

import static duke.util.Keyword.KEYWORD_EVENT_EXCEPTION;

public class InvalidFormatEventException extends DukeException {
    public InvalidFormatEventException() {
        super(KEYWORD_EVENT_EXCEPTION);
    }
}
