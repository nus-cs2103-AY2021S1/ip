package duke.exception;

import static duke.util.Keyword.KEYWORD_FIND_EXCEPTION;

public class InvalidFormatFindException extends DukeException {
    public InvalidFormatFindException() {
        super(KEYWORD_FIND_EXCEPTION);
    }
}
