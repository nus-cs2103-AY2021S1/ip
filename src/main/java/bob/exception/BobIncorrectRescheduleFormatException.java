package bob.exception;

/**
 * An exception to be thrown when an reschedule command is provided in the incorrect format.
 */
public class BobIncorrectRescheduleFormatException extends BobException {

    /**
     * Returns a message indicating the correct format for reschedule commands.
     *
     * @return a message indicating the correct format for reschedule commands.
     */
    public String getMessage() {
        return "Please try to use the following format to reschedule a task:"
                + "\n\t snooze [index] /to [date 1] (YYYY-MM-DD) to [date 2] (YYYY-MM-DD)";
    }
}
