package clippy.exception;

/**
 * Represents an exception due to missing date/time in a command execution.
 */
public class EmptyDateTimeException extends ClippyException {
    /**
     * Constructs an EmptyDateTimeException with a pre-defined error message.
     */
    public EmptyDateTimeException() {
        super("Date/Time cannot be empty! Please retry with specified date/time.");
    }
}
