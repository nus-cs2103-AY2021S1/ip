package focus.exception;

/** Represents exception for inputs which Focus thinks that user wants to input 'remind'. */
public class InvalidRemindCommandException extends FocusException {
    /** Creates InvalidRemindCommandException for RemindCommand class to throw. */
    public InvalidRemindCommandException() {
        super("Hmm, did you meant the command 'remind'?");
    }
}
