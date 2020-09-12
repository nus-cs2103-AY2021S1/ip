package clippy.exception;

public class EmptyDateTimeException extends ClippyException {
    public EmptyDateTimeException() {
        super("Date/Time cannot be empty! Please retry with given date/time.");
    }
}
