package duke.exception;

public abstract class InvalidTaskException extends DukeException {
    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
    }
}
