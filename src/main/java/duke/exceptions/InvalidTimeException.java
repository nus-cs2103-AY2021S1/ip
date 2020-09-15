package duke.exceptions;

/**
 * Class to initiate InvalidTimeException.
 * Thrown when the input time for event and deadline command is wrong.
 */
public class InvalidTimeException extends DukeException {
    /**
     * Initializes InvalidTimeException.
     */
    public InvalidTimeException() {
        super("☹ OOPS!!! The format of your input time seems to be wrong.\n"
                + "Try the following time format: YYYY-MM-DD HH:MM");
    }
}
