package duke.exception;

public class EmptyFindException extends DukeException {
    public EmptyFindException() {
        super("The description of a find cannot be empty!");
    }
}
