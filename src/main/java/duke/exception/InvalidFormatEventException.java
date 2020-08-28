package duke.exception;

public class InvalidFormatEventException extends DukeException {
    private static final String EVENT_EXCEPTION = " ☹ OOPS! A proper event format would be like, "
            + "e.g. event \'event name\' /at \'YYYY-MM-DD HHMM or YYYY-MM-DD\'";
    public InvalidFormatEventException() {
        super(EVENT_EXCEPTION);
    }
}
