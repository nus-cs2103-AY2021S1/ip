/**
 * Thrown to indicate that the user command type is invalid.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs a InvalidCommandException with the specified detail message.
     * @param message Detail message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

}
