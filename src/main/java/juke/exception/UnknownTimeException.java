package juke.exception;

/**
 * Represents a DukeException when a user does not input a time
 * for a Task that requires a time.
 */
public class UnknownTimeException extends DukeException {
    /**
     * Constructs an UnknownTimeException with the appropriate message
     * @param message Error Message to be output to user.
     */
    public UnknownTimeException(String message) {
        super(message);
    }
}
