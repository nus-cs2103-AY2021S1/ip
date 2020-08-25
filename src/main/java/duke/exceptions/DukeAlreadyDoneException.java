package duke.exceptions;

public class DukeAlreadyDoneException extends DukeException {
    final static String ERROR_ALREADY_DONE = "⚠⚠⚠ This task is already done!";

    public DukeAlreadyDoneException() {
        super(ERROR_ALREADY_DONE);
    }
}
