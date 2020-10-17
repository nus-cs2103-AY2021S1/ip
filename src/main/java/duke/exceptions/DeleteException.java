package duke.exceptions;

/**
 * Represents an exception for failing to delete task.
 */
public class DeleteException extends DukeException{
    private static final String ERROR_MSG = "Sorry! We cannot delete like this.";

    /**
     * Creates a {@code DeleteException} with default error message.
     */
    public DeleteException() {
        super(ERROR_MSG);
    }
    /**
     * Creates a {@code DeleteException} with given error message.
     */
    public DeleteException(String errMsg) {
        super(errMsg);
    }
}
