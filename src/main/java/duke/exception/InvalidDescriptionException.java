package duke.exception;

/**
 * Invalid Description Exception that extends Duke Exception class.
 */
public class InvalidDescriptionException extends DukeException {
    /**
     * Constructor for InvalidDescriptionException class.
     */
    public InvalidDescriptionException() {
        super("OOPS! Task was not added! Please start your description exactly 1 space after the task type."
                + " Type 'help' to see the appropriate format.");
    }
}
