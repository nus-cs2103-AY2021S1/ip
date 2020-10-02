package shiro.exception;

/**
 * represents an exception that is thrown when the specified file could not be found
 */
public class ShiroFileNotFoundException extends ShiroException {
    /**
     * constructs a new file not found exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public ShiroFileNotFoundException(String message) {
        super(message);
    }
}
