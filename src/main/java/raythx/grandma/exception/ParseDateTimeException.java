package raythx.grandma.exception;

/**
 * Checked exception as a result of invalid date and time.
 */
public class ParseDateTimeException extends DukeException {
    public ParseDateTimeException() {
        super("Cannot understand ur weird dinosaur timing lah...");
    }
}
