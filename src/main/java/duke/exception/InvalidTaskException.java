package duke.exception;

// Signals that the task provided by the user is formatted incorrectly.
public abstract class InvalidTaskException extends DukeException {
    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
    }
}
