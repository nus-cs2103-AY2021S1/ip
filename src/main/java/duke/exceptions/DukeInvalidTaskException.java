package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the user input does not match any commands.
 */
public class DukeInvalidTaskException extends DukeException {
    public final static String ERROR_INVALID_TASK = "⚠⚠⚠ I'm sorry, but I don't know what that means :-(";

    /**
     * DukeInvalidTaskException constructor.
     */
    public DukeInvalidTaskException() {
        super(ERROR_INVALID_TASK);
    }
}
