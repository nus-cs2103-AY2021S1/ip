package duke.exception;

import static duke.util.Keyword.KEYWORD_EMPTY_EXCEPTION;

public class EmptyTextException extends DukeException {
    public EmptyTextException(String text) {
        super(String.format(KEYWORD_EMPTY_EXCEPTION, text));
    }
}
