package duke.exception;

/**
 * Exception thrown when an event command is entered incorrectly
 */
public class InvalidEventException extends DukeException {
    /**
     * Constructs an InvalidEventException.
     */
    public InvalidEventException() {
        super("You entered the event command incorrectly :(\n"
                + "The command format is \"event <task> /at <dd-MM-yyyy hhmm>\"");
    }
}
