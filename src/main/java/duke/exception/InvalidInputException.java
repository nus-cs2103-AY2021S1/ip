package duke.exception;

/**
 * Represents an InvalidInputException and handles the exceptions related
 * to invalid user inputs.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs an InvalidInputException.
     *
     * @param message Error message to be sent to the user.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

