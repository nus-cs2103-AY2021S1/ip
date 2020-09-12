package duke.exception;

/**
 * represents an exception that is thrown when there is an error loading a file
 */
public class DukeFileLoadingErrorException extends DukeException {
    /**
     * constructs a new file loading error exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public DukeFileLoadingErrorException(String message){
        super(message);
    }
}
