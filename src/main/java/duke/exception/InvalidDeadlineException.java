package duke.exception;

public class InvalidDeadlineException extends InvalidTaskException {
    private static final String ERROR_MESSAGE = "Please input the correct details for the deadline task.\n"
            + "deadline *description* /by *yyyy-mm-dd* *HH:mm*";

    /**
     * Signals that the deadline task provided by the user is formatted incorrectly.
     */
    public InvalidDeadlineException() {
        super(ERROR_MESSAGE);
    }
}
