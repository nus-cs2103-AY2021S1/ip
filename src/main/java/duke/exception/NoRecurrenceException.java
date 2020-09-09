package duke.exception;

public class NoRecurrenceException extends DukeException {
    public NoRecurrenceException() {
        super("Error! No recurrence provided.");
    }
}
