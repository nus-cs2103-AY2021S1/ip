package duke.exceptions;

/**
 * Represents an exception for input with only command but without condition for command.
 */
public class EmptyConditionException extends InvalidInputException {
    private static final String ERROR_MSG = "Sorry! There is no condition for operation.";

    /**
     * Creates an {@code EmptyConditionException} with default error message.
     */
    public EmptyConditionException() {
        super(ERROR_MSG);
    }

    /**
     * Creates an {@code EmptyConditionException} with given error message.
     */
    public EmptyConditionException(String errMsg) {
        super(errMsg);
    }
}
