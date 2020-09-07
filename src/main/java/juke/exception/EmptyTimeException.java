package juke.exception;

/**
 * Represents a DukeException when a user does not input a time
 * for a Task.
 */
public class EmptyTimeException extends DukeException {
    /**
     * Constructs an EmptyTimeException with the appropriate message
     * @param message Error Message to be output to user.
     */
    public EmptyTimeException(String message) {
        super(message);
    }
}
