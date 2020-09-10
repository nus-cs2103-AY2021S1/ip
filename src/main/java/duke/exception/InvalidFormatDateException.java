package duke.exception;

import static duke.util.Keyword.KEYWORD_DATE_EXCEPTION;

public class InvalidFormatDateException extends DukeException {

    public InvalidFormatDateException() {
        super(KEYWORD_DATE_EXCEPTION);
    }
}
