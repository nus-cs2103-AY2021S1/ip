package duke.exception;

/**
 * The exception thrown when the user want to see their task's list
 * when they have not add any task.
 */
public class NoTaskException extends DukeException {

    /**
     * Constructs a NoTaskException with default message.
     * The message is "You haven't add any task!"
     */
    public NoTaskException() {
        super("You haven't add any task!");
    }
}
