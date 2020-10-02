package duke.exception;

/**
 * Represents the exception where the user does not enter an appropriate task type.
 */
public class InvalidTaskTypeException extends DukeException {

    /**
     * Creates an InvalidTaskTypeException object.
     */
    public InvalidTaskTypeException() {
        super("Oops that wasn't a valid task type!\n");
    }
}
