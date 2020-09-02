package duke.exceptions;

public class EmptyToDoException extends DukeException {
    public EmptyToDoException() {
        super("\u2639 OOPS!!! The description of a todo cannot be empty.");
    }
}
