
/**
 * Represents an InvalidDoneException class and handles the exceptions related
 * to invalid DONE command.
 */
public class InvalidDoneException extends InvalidException {

    /**
     * Returns a InvalidDoneException.
     * @param message Message.
     */
    public InvalidDoneException(String message) {
        super(message);
    }
}
