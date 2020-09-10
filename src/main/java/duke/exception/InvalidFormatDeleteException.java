package duke.exception;

import static duke.util.Keyword.KEYWORD_DELETE_EXCEPTION;

public class InvalidFormatDeleteException extends DukeException {
    public InvalidFormatDeleteException() {
        super(KEYWORD_DELETE_EXCEPTION);
    }
}
