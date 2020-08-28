package duke.exception;

public class InvalidFormatDeadlineException extends DukeException {
    private static final String DEADLINE_EXCEPTION = " ☹ OOPS! A proper deadline format would be like, "
            + "e.g. deadline \'duke.task name\' /by \'YYYY-MM-DD HHMM or YYYY-MM-DD\'";
    public InvalidFormatDeadlineException() {
        super(DEADLINE_EXCEPTION);
    }
}
