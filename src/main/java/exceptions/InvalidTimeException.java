package exceptions;

/**
 * Thrown if the date-time field is empty when attempting to add a task
 */
public class InvalidTimeException extends DukeException {
    public InvalidTimeException(String message) {
        super(message);
    }
}
