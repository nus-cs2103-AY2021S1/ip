package duke.exceptions;

/**
 * An exception which indicates that the todo is invalid.
 */
public class InvalidTodoException extends Exception {
    /**
     * Creates a new InvalidTodoException.
     *
     * @param message The message of this exception
     */
    public InvalidTodoException(String message) {
        super(message);
    }
}
