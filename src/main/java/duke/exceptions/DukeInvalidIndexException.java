package duke.exceptions;

public class DukeInvalidIndexException extends DukeException {
    public final static String ERROR_INVALID_INDEX = "⚠⚠⚠ There appears to be a problem with your task number.";

    public DukeInvalidIndexException() {
        super(ERROR_INVALID_INDEX);
    }
}
