package duke.exception;

/**
 * represents an exception that is specific to Duke
 */
public class DukeException extends Exception {
    /**
     * constructs a new Duke exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public DukeException(String message) {
        super(message);
    }
}
