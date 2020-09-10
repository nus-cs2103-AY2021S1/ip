package bob.exception;

/**
 * An exception to be thrown when a required index is not provided.
 */
public class BobNumberFormatException extends BobException {

    /**
     * Returns a message that indicates a required index has not been provided, alongside a format to be followed.
     *
     * @return a message that indicates a required index has not been provided, alongside a format to be followed.
     */
    @Override
    public String getMessage() {
        return "Please provide the index of a task on the list to mark it as done, or to delete, snooze, or schedule "
                + "it.\nHere's the formats: "
                + "\n\tdelete/done [index]\n\tsnooze [index] \to [new deadline]\n\treschedule [index] \to [new period]";
    }
}
