package duke.exception;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("\u2639 OOPS!!! Your deadline command format is wrong!");
    }
}
