package bob.exception;

public class BobIncorrectRescheduleFormatException extends BobException {
    public String getMessage() {
        return "Please try to use the following format to snooze a task:"
                + "\n\t snooze [index] /to [date 1] (YYYY-MM-DD) to [date 2] (YYYY-MM-DD)";
    }
}
