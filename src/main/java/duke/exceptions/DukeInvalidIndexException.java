package duke.exceptions;

public class DukeInvalidIndexException extends DukeException {
    final static String ERROR_INVALID_INDEX = "⚠⚠⚠ There appears to be a problem with your dtask number.";

    public DukeInvalidIndexException() {
        super(ERROR_INVALID_INDEX);
    }
}
