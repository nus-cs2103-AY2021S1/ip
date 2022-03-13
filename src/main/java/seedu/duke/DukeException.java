package seedu.duke;

/**
 * Sends error message.
 */
public class DukeException extends Exception {
    /**
     * Constructor of the class.
     * Sends an error message to the user.
     * @param msg
     */
    public DukeException (String msg) {
        super(msg);
    }
}
