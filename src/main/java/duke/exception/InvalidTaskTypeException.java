package duke.exception;

public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException() {
        super("Invalid Task Type");
    }
    public InvalidTaskTypeException(String message) {
        super(message);
    }
}
