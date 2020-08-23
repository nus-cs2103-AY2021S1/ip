package duke.exception;

public class InvalidEventException extends DukeException {

    public InvalidEventException() {
        super("☹ OOPS!!! Your event command format is wrong!");
    }
}
