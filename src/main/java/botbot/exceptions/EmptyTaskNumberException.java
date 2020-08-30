package botbot.exceptions;

/**
 * Represents an error when a task number is not provided when deleting or marking a task as done.
 */
public class EmptyTaskNumberException extends BotbotException {
    /**
     * Creates an empty task number exception.
     */
    public EmptyTaskNumberException() {
        super("the task number to be marked as done cannot be empty!");
    }
}
