package focus.exception;

/** Represents exception for inputs which Focus thinks that user wants to input 'list'. */
public class InvalidListCommandException extends FocusException {
    /** Creates InvalidListCommandException for ListCommand class to throw. */
    public InvalidListCommandException() {
        super("Hmm, did you meant the command 'list'?");
    }
}
