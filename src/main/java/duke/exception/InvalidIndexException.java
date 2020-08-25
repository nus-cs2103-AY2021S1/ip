package duke.exception;

/**
 * Throws when the index is not an integer, smaller than 0 or larger than the size of the task list.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Creates an <code>InvalidIndexException</code> object.
     */
    public InvalidIndexException() {
        super("Invalid index");
    }
}