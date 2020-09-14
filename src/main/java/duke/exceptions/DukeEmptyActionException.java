package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the description for the action commands is empty.
 */
public class DukeEmptyActionException extends DukeException {

    public static final String ERROR_EMPTY_ACTION_TASK = "Add the appaw-priate number after "
            + "the command 'done/delete'";
    /**
     * DukeEmptyActionException constructor.
     */
    public DukeEmptyActionException() {
        super(ERROR_EMPTY_ACTION_TASK);
    }
}
