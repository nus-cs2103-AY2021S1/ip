package duke.exceptions;

/**
 * Represents an exception for invalid input.
 */
public class InvalidInputException extends DukeException {
    private static final String ERROR_MSG = "Sorry! The input is not valid.";

    /**
     * Creates an {@code InvalidInputException} with default error message.
     */
    public InvalidInputException() {
        super(ERROR_MSG);
    }

    /**
     * Creates an {@code InvalidInputException} with given error message.
     */
    public InvalidInputException(String errMsg) {
        super(errMsg);
    }

}
