package duke.exception;

public class WrongRecurrenceFormatException extends DukeException {
    public WrongRecurrenceFormatException() {
        super("Error! Recurrence is in the wrong format.");
    }
}
