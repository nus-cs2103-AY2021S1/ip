package duke.exception;

public class PastDateTimeException extends DukeException {
    public PastDateTimeException() {
        super("Error! Date/time has already passed.");
    }
}
