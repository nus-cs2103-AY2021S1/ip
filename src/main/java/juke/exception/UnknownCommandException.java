package juke.exception;

/**
 * Represents a DukeException when a user does not input an appropriate
 * command.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructs an UnknownCommandException with the appropriate message
     * @param message Error Message to be output to user.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
