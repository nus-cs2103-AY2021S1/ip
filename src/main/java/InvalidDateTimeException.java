public class InvalidDateTimeException extends DukeException {

    InvalidDateTimeException(String task) {
        super("☹ OOPS!!! Please enter a valid date/time for a " + task + ".");
    }
}
