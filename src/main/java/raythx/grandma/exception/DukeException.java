package raythx.grandma.exception;

/**
 * Checked exceptions for all errors as a result of Duke.
 */
public abstract class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
