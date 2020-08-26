package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the index after an action command is invalid.
 */
public class DukeInvalidIndexException extends DukeException {
    public final static String ERROR_INVALID_INDEX = "⚠⚠⚠ There appears to be a problem with your task number.";

    /**
     * DukeInvalidIndexException constructor.
     */
    public DukeInvalidIndexException() {
        super(ERROR_INVALID_INDEX);
    }
}
