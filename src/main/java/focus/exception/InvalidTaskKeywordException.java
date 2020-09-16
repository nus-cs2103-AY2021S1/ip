package focus.exception;

/** Represents exception for invalid keyword inputs by user when creating tasks. */
public class InvalidTaskKeywordException extends FocusException {
    /** Creates InvalidTaskKeywordException for FindCommand class to throw. */
    public InvalidTaskKeywordException() {
        super("Please enter a keyword you wish to find!");
    }
}
