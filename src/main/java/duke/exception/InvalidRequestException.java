package duke.exception;

import duke.exception.InvalidInputException;

/**
 * The InvalidRequestException class handles the exceptions related
 * to invalid user requests.
 */
public class InvalidRequestException extends InvalidInputException {

    /**
     * Constructs an InvalidRequestException.
     *
     * @param message Error message to be sent to the user.
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}
