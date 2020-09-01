package duke.exception;

public class InvalidEventException extends InvalidTaskException {
    /**
     * Signals that the event task provided by the user is formatted incorrectly.
     */
    public InvalidEventException() {
        super("Please input the correct details for the event task.\n"
                + "event *description* /at *yyyy-mm-dd* *HH:mm*");
    }
}
