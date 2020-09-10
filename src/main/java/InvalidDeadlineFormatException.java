/**
 * Represents an exception thrown when the format of the deadline is incorrect.
 */
public class InvalidDeadlineFormatException extends DukeException {
    InvalidDeadlineFormatException() {
        super("Please enter a valid deadline task\n"
                + "(e.g. deadline xxx /by yyyy-mm-dd HH:mm)");
    }
}
