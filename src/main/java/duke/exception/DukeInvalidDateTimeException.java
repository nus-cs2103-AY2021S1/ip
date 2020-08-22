package duke.exception;

/**
 * Represents a DukeException in which the DateTime object is invalid
 */
public class DukeInvalidDateTimeException extends DukeException{
    /**
     * Constructor.
     * @param message string representing the exception
     */
    public DukeInvalidDateTimeException(String message) {
        super(message);
    }
}
