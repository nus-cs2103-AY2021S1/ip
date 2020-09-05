package seedu.duke.exception;

/**
 * Represents an <code>Exception</code> that occurs when the user inputs a command in the wrong format.
 */
public class InvalidCommandFormatException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message detailed message about the exception
     */
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
