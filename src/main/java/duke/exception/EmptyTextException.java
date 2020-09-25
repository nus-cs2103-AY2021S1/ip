package duke.exception;

import static duke.util.Keyword.KEYWORD_EMPTY_EXCEPTION;

/**
 * Thrown when user did not key in anything after their command.
 */
public class EmptyTextException extends DukeException {

    /**
     * Initialize the EmptyTextException Object.
     *
     * @param text The command that is throwing the exception.
     */
    public EmptyTextException(String text) {
        super(String.format(KEYWORD_EMPTY_EXCEPTION, text));
    }
}
