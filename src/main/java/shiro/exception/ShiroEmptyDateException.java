package shiro.exception;

/**
 * represents an exception that is thrown when the date for a Deadline or Event task is empty
 */
public class ShiroEmptyDateException extends ShiroException {
    /**
     * constructs a new empty date exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public ShiroEmptyDateException(String message) {
        super(message);
    }
}
