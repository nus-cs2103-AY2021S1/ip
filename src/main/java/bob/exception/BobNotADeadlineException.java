package bob.exception;

public class BobNotADeadlineException extends BobException {
    public String getMessage() {
        return "This task does not have a deadline and therefore may not be snoozed.";
    }
}
