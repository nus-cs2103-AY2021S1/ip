package exception;

/**
 * Exception that is thrown when there is IO error
 */
public class DukeIoException extends DukeException {
    public DukeIoException() {
        super("IO Exception occurred");
    }
}
