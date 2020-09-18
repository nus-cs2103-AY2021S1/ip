package meimei.botexception;

/**
 * Exception thrown when the bot fails to load data from the source file.
 */
public class LoadFailureException extends BotException {
    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public LoadFailureException(String message) {
        super("I cannot load the source file leh." + message);
    }
}
