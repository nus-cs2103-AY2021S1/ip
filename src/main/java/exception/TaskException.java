package exception;
/**
 * An subclass of Exception.
 * Shows error for tasks.
 */
public class TaskException extends DukeException {
    public TaskException(String msg) {
        super(msg);
    }
}
