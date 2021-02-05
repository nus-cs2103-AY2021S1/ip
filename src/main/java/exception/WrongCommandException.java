package exception;

/**
 * Inherits from the DukeException class and is thrown
 * when the command words are not used.
 */
public class WrongCommandException extends DukeException {

    /**
     * Creates a WrongCommandException object.
     */
    public WrongCommandException() {
        super("Sorry, I did not understand that. Please use the correct command words.");
    }
}
