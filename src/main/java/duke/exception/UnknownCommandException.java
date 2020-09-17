package duke.exception;

import static duke.util.Keyword.KEYWORD_UNKNOWN_COMMAND_EXCEPTION;

/**
 * Thrown when user key in an unknown command.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Initialize the UnknownCommandException Object.
     */
    public UnknownCommandException() {
        super(KEYWORD_UNKNOWN_COMMAND_EXCEPTION);
    }
}
