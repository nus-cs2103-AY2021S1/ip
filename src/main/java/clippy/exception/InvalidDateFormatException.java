package clippy.exception;

public class InvalidDateFormatException extends ClippyException {
    public InvalidDateFormatException() {
        super("Incorrect date format. Retry with YYYY-MM-DD.");
    }
}
