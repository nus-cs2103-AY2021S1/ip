package main.java.duke;

public class DeleteTaskException extends DukeException {
    /**
     * Creates an instance of DeleteTaskException, a custom exception that occurs when deleting tasks.
     * @param error the string representing the error.
     */
    public DeleteTaskException(String error) {
        super(error);
    }
}
