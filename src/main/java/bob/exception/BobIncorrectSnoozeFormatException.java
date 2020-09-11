package bob.exception;

public class BobIncorrectSnoozeFormatException extends BobException {
    public String getMessage() {
        return "Please try to use the following format to snooze a task:"
                + "\n\t snooze [index] /to [deadline] (YYYY-MM-DD)";
    }
}
