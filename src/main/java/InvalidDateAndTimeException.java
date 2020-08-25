/**
 * The exception that is thrown when the user input date and time in wrong format.
 */
public class InvalidDateAndTimeException extends DukeException {
    public InvalidDateAndTimeException() {
        super("Please input date in this format YYYY-MM-DD.\n" +
                "For events, input the time in this format HHMM-HHMM.");

    }
}
