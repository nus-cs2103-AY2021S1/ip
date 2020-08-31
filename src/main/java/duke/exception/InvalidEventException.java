package duke.exception;

public class InvalidEventException extends DukeException {

    public InvalidEventException() {
        super("\u2639 OOPS!!! Your event command format is wrong!");
    }
}
