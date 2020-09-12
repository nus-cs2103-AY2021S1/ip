package shiro.exception;

/**
 * represents an exception that is thrown when the description for a task is empty
 */
public class ShiroEmptyDescriptionException extends ShiroException {
    /**
     * constructs a new empty description exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public ShiroEmptyDescriptionException(String message) {
        super(message);
    }
}
