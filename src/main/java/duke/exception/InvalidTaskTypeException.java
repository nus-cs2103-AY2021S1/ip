package duke.exception;

public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException() {
        super("Oops that wasn't a valid task type!");
    }
}
