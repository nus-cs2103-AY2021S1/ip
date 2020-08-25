package duke.exception;

/**
 * Represents an exception thrown when index is left empty.
 * Index is required for marking task as done or delete task.
 */
public class DukeEmptyIndexException extends DukeIndexException {
    public DukeEmptyIndexException(String type) {
        super(String.format("☹ OOPS!!! The index after %s cannot be empty.", type));
    }
}
