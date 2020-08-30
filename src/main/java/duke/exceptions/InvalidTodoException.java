package duke.exceptions;

/**
 * An exception which indicates that the todo is invalid.
 */
public class InvalidTodoException extends Exception {
    public InvalidTodoException(String message) {
        super(message);
    }
}
