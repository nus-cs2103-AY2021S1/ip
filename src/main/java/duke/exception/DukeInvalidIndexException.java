package duke.exception;

/**
 * Represents an exception thrown when the index is out of Task List range.
 */
public class DukeInvalidIndexException extends DukeIndexException {
    /**
     * Class constructor.
     */
    public DukeInvalidIndexException() {
        super("OPS!!! There is no such task.");
    }
}
