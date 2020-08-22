public class InvalidFormatDeadlineException extends DukeException {
    public InvalidFormatDeadlineException() {
        super(" ☹ OOPS! A proper deadline format would be like, e.g. deadline \'task name\' /by \'YYYY-MM-DD HHMM or " +
                "YYYY-MM-DD\'");
    }
}
