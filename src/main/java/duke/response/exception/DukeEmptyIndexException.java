package duke.response.exception;

import duke.command.exception.DukeIndexException;

/**
 * Represents an exception thrown when index is left empty.
 * Index is required for marking task as done or delete task.
 */
public class DukeEmptyIndexException extends DukeIndexException {
    /**
     * Class constructor.
     *
     * @param type The type of the task.
     */
    public DukeEmptyIndexException(String type) {
        super(String.format("OOPS!!! The index after %s cannot be empty.", type));
    }
}
