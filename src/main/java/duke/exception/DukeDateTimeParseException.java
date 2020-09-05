package duke.exception;

/**
 * Represents the exception when the date could not be parsed.
 */
public class DukeDateTimeParseException extends DukeException {

    private static final String ERROR_MESSAGE = "Invalid date format! "
            + "Please use the proper date format i.e. yyyy-MM-dd\n";

    public DukeDateTimeParseException() {
        super(ERROR_MESSAGE);
    }
}
