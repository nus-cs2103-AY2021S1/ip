package duke.exception;

/**
 * Represents an exception thrown when the index is out of Task List range.
 */
public class DukeInvalidIndexException extends DukeIndexException {
    public DukeInvalidIndexException() {
        super("☹ OOPS!!! There is no such task.");
    }
}
