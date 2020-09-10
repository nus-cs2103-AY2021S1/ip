package duke.exception;

import static duke.util.Keyword.KEYWORD_HELP_EXCEPTION;

public class InvalidFormatHelpException extends DukeException {
    public InvalidFormatHelpException() {
        super(KEYWORD_HELP_EXCEPTION);
    }
}
