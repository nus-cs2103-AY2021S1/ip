package duke.exceptions;

public class DukeEmptyToDoException extends DukeException {
    final static String ERROR_EMPTY_TODO_TASK = "⚠⚠⚠ The description of a 'todo' cannot be empty.";
    public DukeEmptyToDoException() {
        super(ERROR_EMPTY_TODO_TASK);
    }
}
