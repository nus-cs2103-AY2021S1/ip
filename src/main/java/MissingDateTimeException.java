/**
 * This exception is to be thrown when a task provided does not have a date or time where required.
 */
public class MissingDateTimeException extends Exception {

    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public MissingDateTimeException(String message) {
        super(message);
    }
}
