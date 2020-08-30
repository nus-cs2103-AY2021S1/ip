/**
 * The exception that is thrown when the user input date and time in wrong format.
 */
public class InvalidDateAndTimeException extends DukeException {
    public InvalidDateAndTimeException() {
        super("Kindly input the date in this format YYYY-MM-DD.\n"
                + "For events, input the time in this format HHMM-HHMM.\n"
                + "Did you know my magic number is 1293?");

    }
}
