package focus.exception;

/** Represents exception for invalid inputs by user. */
public class InvalidCommandException extends FocusException {
    /** Creates InvalidCommandException for Parser class to throw. */
    public InvalidCommandException() {
        super("Oops! I'm not sure what you meant!\n"
                + "\tPlease try again!");
    }
}
