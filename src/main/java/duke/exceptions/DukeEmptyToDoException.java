package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the description for the to do command is empty.
 */
public class DukeEmptyToDoException extends DukeException {
    public final static String ERROR_EMPTY_TODO_TASK = "⚠⚠⚠ The description of a 'todo' cannot be empty.";

    /**
     * DukeEmptyToDoException constructor.
     */
    public DukeEmptyToDoException() {
        super(ERROR_EMPTY_TODO_TASK);
    }
}
