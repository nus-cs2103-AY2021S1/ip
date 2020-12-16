/**
 * Represents an InvalidTodoException class and handles the exceptions related
 * to a invalid TODO tasks assign.
 */
public class InvalidTodoException extends InvalidException {

    /**
     * Returns a InvalidTodoException.
     * @param message Message.
     */
    public InvalidTodoException(String message) {
        super(message);
    }
}
