package duke.exception;

/**
 * Invalid Index Exception that extends Duke Exception class.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor for InvalidIndexException class.
     */
    public InvalidIndexException() {
        super("OOPS! Command was not executed! Please choose an index that is within range.");
    }
}
