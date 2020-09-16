package focus.exception;

/** Represents exception for invalid task number inputs by user when creating tasks. */
public class InvalidTaskNumberException extends FocusException {
    /** Creates InvalidTaskNumberException for Delete and Done classes to throw. */
    public InvalidTaskNumberException() {
        super("There is no such task number.\n"
                + "\tPlease enter a valid one!\n"
                + "\tType 'list' to view your list of tasks!");
    }
}
