/**
 * Thrown to indicate that the date of a deadline of event does not have the
 * appropriate format.
 */
public class DateException extends Exception {

    /**
     * Constructs a DateException with the specified detail message.
     * @param message Detail message.
     */
    public DateException(String message) {
        super(message);
    }
}
