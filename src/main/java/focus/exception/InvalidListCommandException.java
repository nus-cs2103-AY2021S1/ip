package focus.exception;

/** Represents exception when user wants to view task list but there is no tasks. */
public class InvalidListCommandException extends FocusException {
    /** Creates InvalidListCommandException for ListCommand class to throw. */
    public InvalidListCommandException() {
        super("\tThere are currently no tasks on your list!\n"
                + "\tStart adding one now!");
    }
}
