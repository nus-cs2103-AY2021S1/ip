package duke.exception;

public class OutOfBoundsException extends DukeException {
    public OutOfBoundsException() {
        super("Error! Enter a valid task number.");
    }
}
