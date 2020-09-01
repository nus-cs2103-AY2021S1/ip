package dude.util;
/**
 * InvalidCommandException class to denote an issue with the command given by the user.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor for the InvalidCommandException class.
     * @param message the error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
