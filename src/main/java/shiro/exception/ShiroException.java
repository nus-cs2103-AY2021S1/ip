package shiro.exception;

/**
 * represents an exception that is specific to shiro
 */
public class ShiroException extends Exception {
    /**
     * constructs a new shiro exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public ShiroException(String message) {
        super(message);
    }
}
