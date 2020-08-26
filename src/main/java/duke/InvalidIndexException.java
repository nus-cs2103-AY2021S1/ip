package duke;

/**
 * Invalid Index Exception that extends Duke Exception class.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor for InvalidIndexException class.
     */
    public InvalidIndexException() {
        super("OOPS!!! The index you have chosen is out of bounds");
    }
}
