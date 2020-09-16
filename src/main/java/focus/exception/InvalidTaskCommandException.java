package focus.exception;

/** Represents exception for invalid inputs by user when creating tasks. */
public class InvalidTaskCommandException extends FocusException {
    /** Creates InvalidTaskCommandException for AddCommand class to throw. */
    public InvalidTaskCommandException() {
        super("Please input the appropriate command!\n"
                + "\tIf you need an example, type 'help'!");
    }
}
