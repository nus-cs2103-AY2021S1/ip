package main.java;

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
        return "Please provide the index of a task on the list to mark it as done or to delete it.\n"
                + "Here's the format: delete/done [index]";
    }
}
