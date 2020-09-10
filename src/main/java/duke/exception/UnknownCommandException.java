package duke.exception;

import static duke.util.Keyword.KEYWORD_UNKNOWN_COMMAND_EXCEPTION;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super(KEYWORD_UNKNOWN_COMMAND_EXCEPTION);
    }
}
