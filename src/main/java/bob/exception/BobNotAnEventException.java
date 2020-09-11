package bob.exception;

public class BobNotAnEventException extends BobException {
    public String getMessage() {
        return "This task is not an event and therefore may not be rescheduled.";
    }
}
