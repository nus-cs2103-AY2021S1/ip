package duke.exception;

/**
 * Represents an exception relating to an invalid task which does not exist or is not recognised.
 */
public class InvalidTaskException extends DukeException {

    /**
     * Creates and initialises a new InvalidTaskException with a specified error message.
     *
     * @param message String containing the specified error message.
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
