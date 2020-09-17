package duke.exceptions;

/**
 * Represents an exception thrown when the format of the event is incorrect.
 */
public class InvalidEventFormatException extends DukeException {
    /**
     * Represents an invalid event format exception.
     */
    public InvalidEventFormatException() {
        super("Oh no, please enter a valid event task\n"
                + "(e.g. event xxx /at yyyy-mm-dd HH:mm)");
    }
}
