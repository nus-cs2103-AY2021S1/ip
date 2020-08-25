package duke.exception;

/**
 * Exception thrown when a deadline command is entered incorrectly.
 */
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("You entered the deadline command incorrectly :(\n"
                + "The command format is \"deadline <task> /by <yyyy-MM-dd hhmm OR yyyy-MM-dd>\"");
    }
}
