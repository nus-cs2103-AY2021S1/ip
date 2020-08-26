package duke.exception;

public class InvalidEventException extends InvalidTaskException {
    public InvalidEventException() {
        super("Please input the correct details for the event task.\n"
                + "event *description* /at *yyyy-mm-dd* *HH:mm*");
    }
}
