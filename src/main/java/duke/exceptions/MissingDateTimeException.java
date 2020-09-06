package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not attach a date/time to a deadline/event. */
public class MissingDateTimeException extends DukeException {

    /** Constructs a new MissingDateTimeException object with the specified error message. */
    public MissingDateTimeException() {
        super ("Harh? The task does not have a date/time attached.");
    }
}
