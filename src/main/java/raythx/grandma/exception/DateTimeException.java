package raythx.grandma.exception;

/**
 * Checked exception as a result of invalid date and time.
 */
public class DateTimeException extends DukeException {
    public DateTimeException() {
        super("Error when loading date and time details...");
    }
}
