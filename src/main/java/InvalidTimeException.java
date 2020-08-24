public class InvalidTimeException extends DukeException {
    public InvalidTimeException() {
        super("☹ OOPS!!! The format of your input time seems to be wrong.\n"
                + "Try the following time format: YYYY-MM-DD HH:MM");
    }
}
