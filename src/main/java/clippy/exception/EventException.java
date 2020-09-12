package clippy.exception;

public class EventException extends ClippyException {
    public EventException() {
        super("☹ OOPS!!! The description of an event cannot be empty.");
    }
}