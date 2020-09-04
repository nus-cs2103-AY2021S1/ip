package duke.exception;

/**
 * represents an exception that is thrown when the description for a task is empty
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * constructs a new empty description exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
