package duke.exception;

import static duke.util.Keyword.KEYWORD_BYE_EXCEPTION;

public class InvalidFormatByeException extends DukeException {
    public InvalidFormatByeException() {
        super(KEYWORD_BYE_EXCEPTION);
    }
}
