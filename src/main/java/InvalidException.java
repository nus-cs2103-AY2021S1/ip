
/**
 * Represents an InvalidException class and handles the exceptions related
 * to a general invalid user inputs.
 */
public class InvalidException extends Exception {

    /**
     * Returns a InvalidException.
     * @param message Message.
     */
    public InvalidException(String message) {
        super(message);
    }
}
