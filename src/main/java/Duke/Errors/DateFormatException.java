package duke.errors;

/**
 * The type DateFormatException exception.
 */
public class DateFormatException extends DukeException {
    /**
     * Instantiates a new DateFormatException exception.
     */
    public DateFormatException() {
        super("☹ OOPS!!! Please enter date format as yyyy-mm-dd hhmm, e.g. 2019-12-01 1800");
    }
}
