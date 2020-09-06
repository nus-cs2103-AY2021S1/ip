package duke.exception;

/**
 * Represents a DukeException in which an update does not match the task.
 */
public class DukeInvalidUpdateException extends DukeException {
    /**
     * Constructor.
     */
    public DukeInvalidUpdateException() {
        super("You are trying to update something that does not exist!");
    }
}
