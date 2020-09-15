package seedu.duke.exception;

/**
 * Exception that encapsulates what happens when there is an exception in user command inputs.
 */
public class DukeCommandException extends DukeException {
    public DukeCommandException(String message) {
        super(message);
    }
}
