package duke.exception;

import duke.exception.InvalidInputException;

public class InvalidRequestException extends InvalidInputException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
