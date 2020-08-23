package duke.exception;

public class InvalidFormatDateException extends DukeException {
    public InvalidFormatDateException() {
        super(" ☹ OOPS! A proper date format would be YYYY-MM-DD HHMM (e.g. 2019-10-15 1600) or " +
                "YYYY-MM-DD (e.g. 2019-10-15)");
    }
}
