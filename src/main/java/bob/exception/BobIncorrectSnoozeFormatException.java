package bob.exception;

/**
 * An exception to be thrown when an snooze command is provided in the incorrect format.
 */
public class BobIncorrectSnoozeFormatException extends BobException {

    /**
     * Returns a message indicating the correct format for snooze commands.
     *
     * @return a message indicating the correct format for snooze commands.
     */
    public String getMessage() {
        return "Please try to use the following format to snooze a task:"
                + "\n\t snooze [index] /to [deadline] (YYYY-MM-DD)";
    }
}
