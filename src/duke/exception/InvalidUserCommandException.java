package duke.exception;

/**
 * Indicates when a user command is not recognized by the program.
 */
public class InvalidUserCommandException extends IllegalArgumentException {
    /**
     * Creates an new InvalidUserCommandException with the specified error message.
     * 
     * @param message Error message to be displayed to the user via the user interface.
     */
    public InvalidUserCommandException(String message) {
        super(message);
    }
}
