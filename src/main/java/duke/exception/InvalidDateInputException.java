package duke.exception;

/**
 * Encapsulates the exception when an invalid date input is received.
 */
public class InvalidDateInputException extends DukeException {
    /**
     * Initialises a new instance with the a warning to indicate that the date is not a recognised
     * format, as well as the correct date format to use.
     *
     * @param date The invalid date the resulted in this exception.
     */
    public InvalidDateInputException(String date) {
        super(String
                .format("%s is not a recognised date format. Please key in dates in the format " +
                        "yyyy-MM-dd. For example, 2007-12-03.", date));
    }
}
