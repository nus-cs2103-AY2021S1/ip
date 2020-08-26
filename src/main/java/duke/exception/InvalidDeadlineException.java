package duke.exception;

/**
 * The InvalidDeadlineException class handles the exceptions related
 * to invalid deadline tasks.
 */
public class InvalidDeadlineException extends InvalidInputException {

    /**
     * Constructs an InvalidDeadlineException.
     *
     * @param message Error message to be sent to the user.
     */
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
