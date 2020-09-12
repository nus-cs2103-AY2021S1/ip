package duke.exception;

/**
 * Exception thrown when a find command is entered incorrectly.
 */
public class InvalidFindException extends DukeException {
    /**
     * Constructs an InvalidDeadlineException.
     */
    public InvalidFindException() {
        super("The find keywords can't be empty :(");
    }
}
