package duke.exceptions;

/**
 * Represents an exception thrown when the format of the deadline is incorrect.
 */
public class InvalidDeadlineFormatException extends DukeException {
    /**
     * Represents an invalid deadline format exception.
     */
    public InvalidDeadlineFormatException() {
        super("Oh no, please enter a valid deadline task\n"
                + "(e.g. deadline xxx /by yyyy-mm-dd HH:mm)");
    }
}
