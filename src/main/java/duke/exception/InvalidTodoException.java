package duke.exception;


/**
 * The InvalidTodoException class handles the exceptions related
 * to invalid todo tasks.
 */
public class InvalidTodoException extends InvalidInputException {

    /**
     * Constructs an InvalidTodoException.
     *
     * @param message Error message to be sent to the user.
     */
    public InvalidTodoException(String message) {
        super(message);
    }
}
