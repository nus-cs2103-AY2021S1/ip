package exceptions;

/**
 * Thrown if description is empty when attempting to add a task
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String message) {
        super(message);
    }
}