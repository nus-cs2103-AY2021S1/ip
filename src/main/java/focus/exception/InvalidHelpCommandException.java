package focus.exception;

/** Represents exception for inputs which Focus thinks that user wants to input 'help'. */
public class InvalidHelpCommandException extends FocusException {
    /** Creates InvalidHelpCommandException for HelpCommand class to throw. */
    public InvalidHelpCommandException() {
        super("Hmm, did you meant the command 'help'?");
    }
}
