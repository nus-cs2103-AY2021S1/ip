package duke.exception;

import duke.exception.InvalidInputException;

public class InvalidTodoException extends InvalidInputException {
    public InvalidTodoException(String message) {
        super(message);
    }
}
