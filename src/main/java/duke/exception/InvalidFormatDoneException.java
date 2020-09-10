package duke.exception;

import static duke.util.Keyword.KEYWORD_DONE_EXCEPTION;

public class InvalidFormatDoneException extends DukeException {
    public InvalidFormatDoneException() {
        super(KEYWORD_DONE_EXCEPTION);
    }
}
