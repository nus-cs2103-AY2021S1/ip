package duke.exceptions;

public class DukeEmptyFindException extends DukeException {
    public final static String ERROR_ALREADY_DONE = "⚠⚠⚠ Missing keyword to find!";

    public DukeEmptyFindException() {
        super(ERROR_ALREADY_DONE);
    }
}
