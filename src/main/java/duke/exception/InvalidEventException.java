package duke.exception;

// Signals that the event task provided by the user is formatted incorrectly.
public class InvalidEventException extends InvalidTaskException {
    public InvalidEventException() {
        super(
            "Please input the correct details for the event task.\n" +
            "event *description* /at *yyyy-mm-dd* *HH:mm*"
        );
    }
}
