/**
 * This Exception is thrown when an invalid command is given.
 */
public class InvalidCommandException extends Exception {
    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
