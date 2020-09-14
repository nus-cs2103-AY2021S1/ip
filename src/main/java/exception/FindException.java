package exception;
/**
 * A subclass of DukeException.
 * Show error for find command.
 */
public class FindException extends DukeException {
    public FindException(String msg) {
        super(msg);
    }
}
