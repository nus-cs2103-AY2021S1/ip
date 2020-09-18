package clippy.exception;

/**
 * Represents an exception due to user inputting a date in an invalid format.
 */
public class InvalidDateFormatException extends ClippyException {
    /**
     * Constructs an InvalidDateFormatException with a pre-defined error message.
     */
    public InvalidDateFormatException() {
        super("Incorrect date format. Please retry with YYYY-MM-DD.");
    }
}
