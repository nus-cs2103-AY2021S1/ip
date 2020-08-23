public class InvalidDateAndTimeException extends DukeException {
    public InvalidDateAndTimeException() {
        super("Please input date in this format YYYY-MM-DD. " +
                "For events, input the time as HHMM-HHMM.");
    }
}
