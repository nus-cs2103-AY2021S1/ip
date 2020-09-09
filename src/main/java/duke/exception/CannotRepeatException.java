package duke.exception;

public class CannotRepeatException extends DukeException {
    public CannotRepeatException() {
        super("Error! ToDo tasks cannot be repeated.");
    }
}
