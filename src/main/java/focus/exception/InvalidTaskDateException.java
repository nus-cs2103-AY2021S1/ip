package focus.exception;

/** Represents exception for invalid date inputs by user when creating tasks. */
public class InvalidTaskDateException extends FocusException {
    /** Creates InvalidTaskDateException for AddCommand class to throw. */
    public InvalidTaskDateException() {
        super("Please input the correct date format!\n"
                + "\tIf you need an example, type 'help'!");
    }
}
