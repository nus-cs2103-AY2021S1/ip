package seedu.duke.exception;

/**
 * Represents an <code>Exception</code> that occurs when Duke is running.
 */
public class DukeException extends Exception {
    /**
     * Class constructor.
     *
     * @param message detailed message about the exception
     */
    public DukeException(String message) {
        super(message);
    }
}
