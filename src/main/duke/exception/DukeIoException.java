package duke.exception;

/**
 * Exception to be thrown if an error occurs in the IO of execution.
 */
public class DukeIoException extends DukeException {
    public DukeIoException(String error) {
        super(error);
    }
}
