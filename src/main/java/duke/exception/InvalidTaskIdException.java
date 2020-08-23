package duke.exception;

/**
 * Encapsulates the exception when an invalid task ID is received.
 */
public class InvalidTaskIdException extends DukeException {
    /**
     * Initialises a new instance with the specified detail message followed by a warning to key
     * in only the ID of the task.
     *
     * @param message The detail message.
     */
    public InvalidTaskIdException(String message) {
        super(message + " Please key in only the integer representing the task!");
    }
}
