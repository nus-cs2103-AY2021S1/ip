package duke.exception;

import static duke.util.Keyword.KEYWORD_LIST_EXCEPTION;

public class InvalidFormatListException extends DukeException {
    public InvalidFormatListException() {
        super(KEYWORD_LIST_EXCEPTION);
    }
}
