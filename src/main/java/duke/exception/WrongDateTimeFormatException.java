package duke.exception;

public class WrongDateTimeFormatException extends DukeException {
    public WrongDateTimeFormatException() {
        super("Error! Date/time is in the wrong format.");
    }
}
