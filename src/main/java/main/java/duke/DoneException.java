package main.java.duke;

/**
 * An exception that is thrown when the done command fails.
 */
public class DoneException extends DukeException {
    /**
     * Creates an instance of DoneException, a custom exception that occurs when marking tasks as done.
     * @param error the string representing the error.
     */
    public DoneException(String error) {
        super(error);
    }
}
