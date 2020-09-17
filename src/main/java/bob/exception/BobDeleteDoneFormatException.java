package bob.exception;

/**
 * An exception to be thrown when a required index is not provided.
 */
public class BobDeleteDoneFormatException extends BobException {

    /**
     * Returns a message that indicates a required index has not been provided, alongside a format to be followed.
     *
     * @return a message that indicates a required index has not been provided, alongside a format to be followed.
     */
    @Override
    public String getMessage() {
        return "Please provide only the index of a task on the list to mark it as done or to delete it. "
                + "\nOtherwise, you could choose to apply the action to all tasks. \nHere's the "
                + "format: \n\tdelete/done [index]/all";
    }
}
