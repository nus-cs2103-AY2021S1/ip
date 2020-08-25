package duke.task;

/**
 * Represents an exception that happened when the given index for the task is invalid.
 */
public class InvalidTaskIndexException extends TaskException {
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
