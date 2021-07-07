package seedu.duke.exception;

/**
 * Exception that encapsulates what happens when there is an error in user input.
 */
public class DukeNotSureException extends DukeException {
    public DukeNotSureException(String message) {
        super(message);
    }
}
