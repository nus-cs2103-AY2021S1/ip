package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not attach a date/time to a deadline/event. */
public class MissingDateTimeException extends DukeException {

    /** Constructor. */
    public MissingDateTimeException() {
        super ("☹ OOPS!!! The task does not have a date/time attached.");
    }
}
