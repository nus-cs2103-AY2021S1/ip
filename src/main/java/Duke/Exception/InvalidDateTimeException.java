package Duke.Exception;

/**
 * Exception representing invalid date and time input.
 */
public class InvalidDateTimeException extends DukeException {

    public InvalidDateTimeException() {
        super("Invalid Date and Time input. Follow this format: \"yyyy/mm/dd HHmm\"");
    }
}
