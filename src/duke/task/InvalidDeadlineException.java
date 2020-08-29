package duke.task;

/**
 * An exception which indicates that the deadline is invalid.
 */
public class InvalidDeadlineException extends Exception{
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
