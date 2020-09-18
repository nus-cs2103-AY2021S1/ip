package exception;
/**
 * An subclass of TaskException.
 * Shows error for invalid input.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
