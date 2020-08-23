package duke.exception;

public class InvalidDeleteException extends DukeException {

    public InvalidDeleteException() {
        super("OOPS!!! The task to be deleted has to be a number.");
    }
}
