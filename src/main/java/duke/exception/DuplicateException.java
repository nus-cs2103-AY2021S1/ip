package duke.exception;

import static duke.util.Keyword.KEYWORD_DUPLICATE_EXCEPTION;

public class DuplicateException extends DukeException {
    public DuplicateException() {
        super(KEYWORD_DUPLICATE_EXCEPTION);
    }
}
