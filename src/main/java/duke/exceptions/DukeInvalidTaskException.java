package duke.exceptions;

public class DukeInvalidTaskException extends DukeException {
    public final static String ERROR_INVALID_TASK = "⚠⚠⚠ I'm sorry, but I don't know what that means :-(";

    public DukeInvalidTaskException() {
        super(ERROR_INVALID_TASK);
    }
}
