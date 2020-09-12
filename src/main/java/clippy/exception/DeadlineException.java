package clippy.exception;

public class DeadlineException extends ClippyException {
    public DeadlineException() {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}