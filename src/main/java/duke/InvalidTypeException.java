package duke;

/**
 * Invalid Type Exception that extends Duke Exception class.
 */
public class InvalidTypeException extends DukeException {
    /**
     * Constructor for InvalidTypeException class.
     */
    public InvalidTypeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}