package duke.exceptions;

/** Thrown to indicate that the user input an invalid date or time format. */
public class DukeDateTimeParseException extends DukeException {

    /** Constructs a DukeDateTimeParseException with a relevant detail message. */
    public DukeDateTimeParseException() {
        super("OOPS! Invalid date / time format!");
    }
}
