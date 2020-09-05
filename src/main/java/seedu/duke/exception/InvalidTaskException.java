package seedu.duke.exception;

/**
 * Represents an <code>Exception</code> that occurs when the user inputs a position where there is no task.
 */
public class InvalidTaskException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message detailed message about the exception
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
