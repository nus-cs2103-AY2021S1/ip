package duke.exception;

/**
 * Represents an exception when there no such type of Task.
 */
public class InvalidTaskTypeException extends DukeException {

    /**
     * Constructs an InvalidTaskTypeException.
     */
    public InvalidTaskTypeException() {
        super("OOPS!!! No such task type.");
    }
}

