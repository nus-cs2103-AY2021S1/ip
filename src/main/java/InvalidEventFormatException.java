/**
 * Represents an exception thrown when the format of the event is incorrect.
 */
public class InvalidEventFormatException extends DukeException {
    InvalidEventFormatException() {
        super("Please enter a valid event task\n"
                + "(e.g. event xxx /at yyyy-mm-dd HH:mm)");
    }
}
