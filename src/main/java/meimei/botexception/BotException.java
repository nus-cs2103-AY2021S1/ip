package meimei.botexception;

/**
 * Represents exceptions unique to the bot.
 */
public class BotException extends Exception {

    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public BotException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Aiyo! " + super.getMessage();
    }
}
