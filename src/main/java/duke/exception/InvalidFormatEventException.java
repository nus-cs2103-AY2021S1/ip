package duke.exception;

public class InvalidFormatEventException extends DukeException {
    public InvalidFormatEventException() {
        super(" ☹ OOPS! A proper event format would be like, e.g. event \'event name\' /at \'YYYY-MM-DD HHMM or " +
                "YYYY-MM-DD\'");
    }
}
