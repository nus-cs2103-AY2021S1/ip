package duke.task;

/**
 * An exception which indicates that the todo is invalid.
 */
public class InvalidTodoException extends Exception {
    public InvalidTodoException(String message) {
        super(message);
    }
}
