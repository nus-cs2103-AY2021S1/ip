package duke.exception;

// Signals that the deadline task provided by the user is formatted incorrectly.
public class InvalidDeadlineException extends InvalidTaskException {
    public InvalidDeadlineException() {
        super("Please input the correct details for the deadline task.\n"
                + "deadline *description* /by *yyyy-mm-dd* *HH:mm*");
    }
}
