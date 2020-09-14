package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the date and time input is not in the proper format.
 */
public class DukeDateTimeParseException extends DukeException {

    public static final String ERROR_INVALID_DATE = "Please input the paw-per date in this "
            + "format: 'yyyy-MM-dd HH:mm'";
    /**
     * DukeDateTimeParseException constructor.
     */
    public DukeDateTimeParseException() {
        super(ERROR_INVALID_DATE);
    }
}
