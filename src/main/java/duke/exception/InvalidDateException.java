package duke.exception;

/**
 * Exception thrown when a given date has an invalid format.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructs an InvalidDateException.
     */
    public InvalidDateException(String errorMessage) {
        super(errorMessage);
    }
}
