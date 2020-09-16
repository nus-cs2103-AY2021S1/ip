package duke.exception;

/**
 * Exception representing invalid date and time input.
 */
public class InvalidDateTimeException extends DukeException {

    public InvalidDateTimeException() {
        super("Invalid Date and Time input.\n" + "(Format: yyyy/mm/dd HHmm)");
    }
}
