package duke.exception;

/**
 * Represents an exception when user does not input a task number to deleted.
 */
public class InvalidDeleteException extends DukeException {

    /**
     * Constructs an InvalidDeleteException.
     */
    public InvalidDeleteException() {
        super("OOPS!!! The task to be deleted has to be a number.");
    }
}
