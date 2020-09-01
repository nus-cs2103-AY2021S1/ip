package duke.exception;

/**
 * This exception is thrown when a task at an invalid index is referenced.
 */
public class InvalidIndexException extends Exception {
    @Override
    public String toString() {
        return "Fool, there is no task associated with this number!";
    }
}
