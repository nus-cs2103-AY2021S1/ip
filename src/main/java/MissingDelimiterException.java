/**
 * Exception to be thrown when a command to add task does not have a delimiter for date/time where required.
 */
public class MissingDelimiterException extends Exception {

    /**
     * Public Constructor.
     *
     * @param message Error message.
     */
    public MissingDelimiterException(String message) {
        super(message);
    }
}
