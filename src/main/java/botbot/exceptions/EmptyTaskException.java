package botbot.exceptions;

/**
 * Represents an error when a task description is not provided.
 */
public class EmptyTaskException extends BotbotException {
    /**
     * Creates an empty task exception.
     *
     * @param e Type of task.
     */
    public EmptyTaskException(String e) {
        super("the description of " + e + " cannot be empty!");
    }
}
