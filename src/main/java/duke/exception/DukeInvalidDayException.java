package duke.exception;

public class DukeInvalidDayException extends DukeException {
    public DukeInvalidDayException() {
        super("Day of event is invalid :(, please do not leave it as empty");
    }
}
