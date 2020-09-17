package duke.exception;

/**
 * Invalid Type Exception that extends Duke Exception class.
 */
public class InvalidTypeException extends DukeException {
    /**
     * Constructor for InvalidTypeException class.
     */
    public InvalidTypeException() {
        super("OOPS! Command was not executed! Please use a valid command. Type 'help' for more information.");
    }
}
