package duke.exceptions;

/**
 * Class to initiate InvalidEventFormatException.
 * Thrown when the format for event command is wrong.
 */
public class InvalidEventFormatException extends DukeException {
    /**
     * Initializes InvalidEventFormatException.
     */
    public InvalidEventFormatException() {
        super("☹ OOPS!!! The format of event command seems to be wrong.\n"
                + "Try the following format: event task /at YYYY-MM-DD HH:MM");
    }
}
