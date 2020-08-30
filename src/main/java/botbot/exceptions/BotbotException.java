package botbot.exceptions;

/**
 * Represents an error specific to the bot.
 */
public class BotbotException extends Exception {
    /**
     * Creates a botbot exception.
     * 
     * @param e Error message.
     */
    public BotbotException(String e) {
        super("    oops! " + e + "\n");
    }
}
