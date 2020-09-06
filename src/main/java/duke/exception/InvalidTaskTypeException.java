package duke.exception;

/**
 * Represents an exception when user does not provide any keyword/keyphrase.
 */
public class InvalidTaskTypeException extends DukeException {

    /**
     * Constructs a InvalidTaskTypeException.
     */
    public InvalidTaskTypeException() {
        super("OOPS!!! No such Task type.");
    }
}

