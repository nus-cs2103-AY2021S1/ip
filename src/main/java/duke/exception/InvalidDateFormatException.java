package duke.exception;

/**
 * Throws when the date is in incorrect format.
 */
public class InvalidDateFormatException extends DukeException {
    private static String INCORRECT_DATE_MSG = "Sorry, I cannot understand the date, "
            + "try to format it in the format: yyyy-MM-dd";

    /**
     * Creates an <code>InvalidDateFormatException</code> object.
     */
    public InvalidDateFormatException() {
        super(INCORRECT_DATE_MSG);
    }
}
