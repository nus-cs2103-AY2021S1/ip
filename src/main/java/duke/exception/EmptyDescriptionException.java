package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("Oops, the description can't be empty!\n");
    }
}
