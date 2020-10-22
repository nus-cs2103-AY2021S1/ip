package clippy.exception;

/**
 * Represents an exception due to user inputting an invalid command.
 */
public class InvalidCommandException extends ClippyException {
    /**
     * Constructs an InvalidCommandException with a pre-defined error message.
     */
    public InvalidCommandException() {
        super("Oops! I'm sorry, but I don't know what that means. :(");
    }
}
