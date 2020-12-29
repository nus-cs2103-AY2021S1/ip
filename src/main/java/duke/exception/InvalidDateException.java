package duke.exception;

/**
 * Exception thrown when date is invalid.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidDateException class.
     */
    public InvalidDateException() {
        super("OOPS! Task was not added! Please use the yyyy-MM-dd format for your date.");
    }
}
