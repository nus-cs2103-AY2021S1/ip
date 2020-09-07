package duke.exception;

/**
 * Represents the exception when the date could not be parsed.
 */
public class DukeDateTimeParseException extends DukeException {

    private static final String ERROR_MESSAGE_DATE = "Invalid date format! "
            + "Please use the proper date format i.e. dd/MM/yyyy\n";

    private static final String ERROR_MESSAGE_TIME = "Invalid date-time format! "
            + "Please use the proper date-time format i.e. dd/MM/yyyy HH:mm";

    public DukeDateTimeParseException(boolean hasTime) {
        super(hasTime ? ERROR_MESSAGE_TIME : ERROR_MESSAGE_DATE);
    }
}
