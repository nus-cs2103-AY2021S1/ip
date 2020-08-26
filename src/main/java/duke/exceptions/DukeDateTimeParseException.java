package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the date and time input is not in the proper format.
 */
public class DukeDateTimeParseException extends DukeException {
    public final static String ERROR_INVALID_DATE = "⚠⚠⚠ Please input the proper date in this"
            + '\n' + "     " + " format: 'yyyy-MM-dd HH:mm'";

    /**
     * DukeDateTimeParseException constructor.
     */
    public DukeDateTimeParseException() {
        super(ERROR_INVALID_DATE);
    }
}
