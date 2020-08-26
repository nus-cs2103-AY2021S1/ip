package duke.exception;

/**
 * The InvalidEventException class handles the exceptions related
 * to invalid event tasks.
 */
public class InvalidEventException extends InvalidInputException {

    /**
     * Constructs an InvalidEventException.
     *
     * @param message Error message to be sent to the user.
     */
    public InvalidEventException(String message) {
        super(message);
    }
}
