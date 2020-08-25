package duke.exception;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("You entered the deadline command incorrectly :(\n"
                + "The command format is \"deadline <task> /by <yyyy-MM-dd hhmm OR yyyy-MM-dd>\"");
    }
}
