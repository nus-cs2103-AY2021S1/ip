package duke.exception;

public class InvalidDeadlineException extends InvalidTaskException {
    public InvalidDeadlineException() {
        super(
            "Please input the correct details for the deadline task.\n" +
            "deadline *description* /by *yyyy-mm-dd* *HH:mm*"
        );
    }
}
