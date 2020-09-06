package duke.exceptions;

/**
 * An exception which indicates that the event is invalid.
 */
public class InvalidEventException extends Exception {
    /**
     * Creates a new InvalidEventException.
     *
     * @param message The message of this exception
     */
    public InvalidEventException(String message) {
        super(message);
    }
}
