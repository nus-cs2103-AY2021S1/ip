package duke.error;

/**
 * Represents an error that occurs when an incorrect date is passed when create an Event instance.
 *
 * @author Roger Lim
 */
public class EventDateParseException extends Exception {
    public EventDateParseException(String date) {
        super(String.format("Please the correct format, %s is\nnot a valid date", date));
    }
}
