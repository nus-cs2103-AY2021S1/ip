package duke.exception;

/** An exception for the Duke bot. */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the given message.
     * @param msg The message to be printed.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
