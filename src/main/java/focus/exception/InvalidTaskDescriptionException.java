package focus.exception;

/** Represents exception for invalid description inputs by user when creating tasks. */
public class InvalidTaskDescriptionException extends FocusException {
    /** Creates InvalidTaskDescriptionException for AddCommand class to throw. */
    public InvalidTaskDescriptionException() {
        super("Please input an appropriate task description!\n"
                + "\tIf you need an example, type 'help'!");
    }
}
