package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Error! Date/time is invalid.");
    }
}
