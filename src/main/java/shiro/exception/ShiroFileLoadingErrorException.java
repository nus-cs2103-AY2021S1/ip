package shiro.exception;

/**
 * represents an exception that is thrown when there is an error loading a file
 */
public class ShiroFileLoadingErrorException extends ShiroException {
    /**
     * constructs a new file loading error exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public ShiroFileLoadingErrorException(String message){
        super(message);
    }
}
