package exceptions;

/**
 * Thrown if a non-integer argument is entered when attempting to delete or mark a task as done
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
