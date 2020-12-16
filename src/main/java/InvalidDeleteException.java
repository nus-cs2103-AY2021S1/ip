
/**
 * Represents an InvalidDeleteException class and handles the exceptions related
 * to invalid DETELE command.
 */
public class InvalidDeleteException extends InvalidException {
    /**
     * Returns a InvalidDeleteException.
     * @param message Message.
     */
    public InvalidDeleteException(String message) {
        super(message);
    }
}
