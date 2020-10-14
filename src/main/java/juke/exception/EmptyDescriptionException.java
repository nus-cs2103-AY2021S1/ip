package juke.exception;

/**
 * Represents a DukeException when a user does not input a description
 * for a Task.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs an EmptyDescriptionException with the appropriate message
     * @param message Error Message to be output to user.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
