
/**
 * Represents an InvalidDeadlineException class and handles the exceptions related
 * to invalid deadline tasks assigned.
 */
public class InvalidDeadlineException extends InvalidException {

    /**
     * Returns a InvalidDeadlineException.
     * @param message Message.
     */
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
