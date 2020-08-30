package botbot.exceptions;

/**
 * Represents an error when the provided command does not exist.
 */
public class NoSuchCommandException extends BotbotException {
    /**
     * Creates a no such command exception.
     */
    public NoSuchCommandException() {
        super("sorry, I don't know what that means!");
    }
}
