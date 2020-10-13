package duke.exception;

/**
 * A customised Exception class for BrawlyDuke.
 */
public class DukeException extends Exception {
    /**
     * Constructor of a new exception with the specified message.
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
