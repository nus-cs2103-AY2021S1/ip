package duke.exceptions;

/**
 * Class to initiate InvalidFindFormatException.
 * Thrown when the format for find command is wrong.
 */
public class InvalidFindFormatException extends DukeException {
    /**
     * Initializes InvalidFindFormatException.
     */
    public InvalidFindFormatException() {
        super("☹ OOPS!!! The format of find command seems to be wrong.\n"
                + "Try the following format: find keyword");
    }
}
