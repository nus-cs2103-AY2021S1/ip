package exception;

public class NumberOutOfRangeException extends DukeException {

    public NumberOutOfRangeException() {
        super("This task does not exist. Please key the corresponding task number as seen in the list.");
    }
}
