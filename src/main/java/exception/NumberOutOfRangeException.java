package exception;

public class NumberOutOfRangeException extends DukeException {

    public NumberOutOfRangeException() {
        super("Invalid task number.");
    }
}
