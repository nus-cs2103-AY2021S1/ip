package duke.exception;

/**
 * Represents a DukeException in which the index is invalid.
 */
public class DukeInvalidIndexException extends DukeException {
    /**
     * Constructor.
     */
    public DukeInvalidIndexException() {
        super("Please enter a valid index!");
    }
}
