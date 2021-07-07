package duke.exceptions;

/**
 * Class to initiate InvalidDeadlineFormatException.
 * Thrown when the format for deadline command is wrong.
 */
public class InvalidDeadlineFormatException extends DukeException {
    /**
     * Constructor for InvalidDeadlineFormatException.
     */
    public InvalidDeadlineFormatException() {
        super("☹ OOPS!!! The format of deadline command seems to be wrong.\n"
                + "Try the following format: deadline task /by YYYY-MM-DD HH:MM");
    }
}
