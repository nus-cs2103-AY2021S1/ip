package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the description for the to do command is empty.
 */
public class DukeEmptyToDoException extends DukeException {

    public static final String ERROR_EMPTY_TODO_TASK = "You fur-get some todo details.";


    /**
     * DukeEmptyToDoException constructor.
     */
    public DukeEmptyToDoException() {
        super(ERROR_EMPTY_TODO_TASK);
    }
}
