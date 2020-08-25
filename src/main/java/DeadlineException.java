/**
 * A subclass of TaskException.
 * Shows error for deadline tasks.
 */
public class DeadlineException extends TaskException {

    public DeadlineException(String msg) {
        super(msg);
    }
}
