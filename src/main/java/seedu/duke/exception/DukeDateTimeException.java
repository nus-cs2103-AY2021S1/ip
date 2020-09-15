package seedu.duke.exception;

/**
 * Exception that encapsulates what happens when there is an error in the date and time format of tasks.
 */
public class DukeDateTimeException extends DukeException {
    public DukeDateTimeException(String message) {
        super(message);
    }
}
