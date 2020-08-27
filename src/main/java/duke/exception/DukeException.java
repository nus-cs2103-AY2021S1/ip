package duke.exception;

/**
 * Exception class for handling errors related to Duke.
 */
public class DukeException extends Exception {
    private final String e;

    /**
     * Default constructor.
     */
    public DukeException() {
        this.e = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Creates a new DukeException with the given message.
     * @param e Error message to be shown.
     */
    public DukeException(String e) {
        this.e = "☹ OOPS!!! " + e;
    }

    @Override
    public String toString() {
        return e;
    }
}
