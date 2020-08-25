package duke.exception;

/**
 * Represents the possible exceptions that can be thrown when the type of task in invalid.
 */
public class InvalidTaskTypeException extends DukeException {
    /**
     * Instantiates a new InvalidTaskTypeException object.
     * @param errorMessage The detail error message.
     */
    public InvalidTaskTypeException(String errorMessage) {
        super(errorMessage);
    }
}
