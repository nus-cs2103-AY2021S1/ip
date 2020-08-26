package duke.exception;

/**
 * The exception thrown when the user input invalid data
 * such as characters.
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Constructs an InvalidTaskNumberException with default message.
     * The message is "Invalid task number!"
     */
    public InvalidTaskNumberException() {
        super("Invalid task number!");
    }
}
