package main.java.duke;

/**
 * An exception that is thrown when the delete command fails due to an incorrect command format.
 */
public class DeleteTaskException extends DukeException {
    /**
     * Creates an instance of DeleteTaskException, a custom exception that occurs when deleting tasks.
     * @param error the string representing the error.
     */
    public DeleteTaskException(String error) {
        super(error);
    }
}
