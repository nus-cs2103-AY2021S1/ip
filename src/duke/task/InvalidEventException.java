package duke.task;

/**
 * An exception which indicates that the event is invalid.
 */
public class InvalidEventException extends Exception {
    public InvalidEventException(String message) {
        super(message);
    }
}
