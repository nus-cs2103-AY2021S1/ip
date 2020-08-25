package duke.exceptions;

public class DukeEmptyActionException extends DukeException {
    final static String ERROR_EMPTY_ACTION_TASK = "⚠⚠⚠ Add the appropriate duke.task number after the command "
            + "'done/delete'.";
    public DukeEmptyActionException() {
        super(ERROR_EMPTY_ACTION_TASK);
    }
}
