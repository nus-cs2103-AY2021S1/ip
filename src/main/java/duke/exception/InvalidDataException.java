package duke.exception;

/**
 * Invalid Data Exception that extends Duke Exception class.
 */
public class InvalidDataException extends DukeException {
    /**
     * Constructor for InvalidDataException class.
     */
    public InvalidDataException() {
        super("OOPS! The data here is invalid! Initialising with empty array.");
    }
}
