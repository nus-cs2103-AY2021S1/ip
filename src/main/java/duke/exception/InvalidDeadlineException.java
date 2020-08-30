package duke.exception;

/**
 * Exception thrown when a deadline command is entered incorrectly.
 */
public class InvalidDeadlineException extends DukeException {
    /**
     * Constructs an InvalidDeadlineException.
     */
    public InvalidDeadlineException() {
        super("You entered the deadline command incorrectly :(\n"
                + "The command format is \"deadline <task> /by <dd-MM-yyyy hhmm>\"");
    }
}
