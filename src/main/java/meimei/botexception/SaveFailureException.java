package meimei.botexception;

/**
 * Exception thrown when bot is unable to save tasks to source file.
 */
public class SaveFailureException extends BotException {

    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public SaveFailureException(String message) {
        super("I cannot save to the source file leh." + message);
    }
}
