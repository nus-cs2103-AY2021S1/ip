package duke.exception;

/**
 * Checked exceptions for all errors as a result of Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
