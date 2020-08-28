package duke.exception;

public class InvalidFormatDateException extends DukeException {
    private static final String DATE_EXCEPTION = " ☹ OOPS! A proper date format would be YYYY-MM-DD HHMM "
            + "(e.g. 2019-10-15 1600) or YYYY-MM-DD (e.g. 2019-10-15)";
    public InvalidFormatDateException() {
        super(DATE_EXCEPTION);
    }
}
