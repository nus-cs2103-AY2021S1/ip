package meimei.dukeexception;

/**
 * Exception thrown when bot is unable to save tasks to source file.
 */
public class SaveFailureException extends DukeException {
    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public SaveFailureException(String message) {
        super("I cannot save to the source file leh." + message);
    }
}
