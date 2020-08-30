package botbot.exceptions;

/**
 * Represents an error when an invalid format is used when adding a task.
 */
public class InvalidFormatException extends BotbotException {
    /**
     * Creates an invalid format exception.
     *
     * @param e Valid format for type of task to be added.
     */
    public InvalidFormatException(String e) {
        super("invalid format! please follow '" + e + "'!");
    }
}
