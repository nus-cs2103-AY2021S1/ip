package duke.error;

/**
 * Represents an error that occurs when an incorrect date is passed when create a Deadline instance.
 *
 * @author Roger Lim
 */
public class DeadlineDateParseException extends Exception {
    public DeadlineDateParseException(String date) {
        super(String.format("Please the correct format, %s is\n not a valid date", date));
    }
}
