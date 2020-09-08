package raythx98.grandma.exception;

/**
 * Checked exception as a result of being unable to load task.
 */
public class DateTimeException extends DukeException {
    public DateTimeException() {
        super("Error when loading date and time details...");
    }
}
