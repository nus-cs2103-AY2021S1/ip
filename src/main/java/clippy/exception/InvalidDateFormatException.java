package clippy.exception;

public class InvalidDateFormatException extends ClippyException {
    public InvalidDateFormatException() {
        super("Incorrect date format. Please retry with YYYY-MM-DD.");
    }
}
