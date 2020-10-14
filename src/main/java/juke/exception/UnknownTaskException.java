package juke.exception;

/**
 * Represents a DukeException when a user does not input a proper task.
 */
public class UnknownTaskException extends DukeException {
    /**
     * Constructs an UnknownTaskException with the appropriate message
     * @param message Error Message to be output to user.
     */
    public UnknownTaskException(String message) {
        super(message);
    }
}
