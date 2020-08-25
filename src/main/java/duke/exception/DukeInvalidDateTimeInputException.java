package duke.exception;

/**
 * Represents an exception thrown when the Date or Time input is invalid.
 */
public class DukeInvalidDateTimeInputException extends DukeTaskException {
    public DukeInvalidDateTimeInputException(String message) {
        super(message);
    }
}
