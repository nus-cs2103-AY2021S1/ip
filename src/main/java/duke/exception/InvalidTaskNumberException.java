package duke.exception;

/**
 * The Invalid task number exception.
 */
public class InvalidTaskNumberException extends InvalidInputException {
    /**
     * Instantiates a new Invalid task number exception.
     */
    public InvalidTaskNumberException() {
        super("The task number is invalid!");
    }

    /**
     * Instantiates a new Invalid task number exception.
     *
     * @param message the message.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
