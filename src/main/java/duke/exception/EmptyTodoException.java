package duke.exception;

/**
 * Thrown when issued todo input has no description.
 */
public class EmptyTodoException extends Exception {

    /**
     * Constructs the exception.
     */
    public EmptyTodoException() {
        super("\uD83D\uDE41 OOPS! The description of a todo cannot be empty.");
    }

    /**
     * String representation of this exception.
     * @return  String representation of this exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
