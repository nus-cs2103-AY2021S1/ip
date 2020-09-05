package seedu.duke.exception;

/**
 * Represents an <code>Exception</code> that occurs when the command given is not supported.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message detailed message about the exception
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
