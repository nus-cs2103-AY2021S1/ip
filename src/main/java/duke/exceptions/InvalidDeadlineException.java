package duke.exceptions;

/**
 * An exception which indicates that the deadline is invalid.
 */
public class InvalidDeadlineException extends Exception{
    /**
     * Create a new InvalidDeadlineException.
     *
     * @param message The message of this exception
     */
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
