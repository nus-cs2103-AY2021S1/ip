package duke.exception;

/**
 * Indicates when a user specifies a task in the task list using a non-numerical number.
 */
public class InvalidIndexNumberException extends NumberFormatException {
    /**
     * Constructs a new InvalidIndexNumberException with no specified detail message.
     */
    public InvalidIndexNumberException() {
        super("OOPS! Please enter a numerical number :)");
    }
}
