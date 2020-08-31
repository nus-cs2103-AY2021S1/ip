package sparrow.data.exceptions;

public class EmptyTodoDescriptionException extends Exception {
    public EmptyTodoDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
