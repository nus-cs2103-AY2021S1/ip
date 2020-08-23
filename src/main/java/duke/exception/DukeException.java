package duke.exception;

/**
 * Represents an Exception that occurred while running Duke.
 */
public class DukeException extends Exception {

    /**
     * Initializes DukeException with an error message.
     *
     * @param msg The error message describing the error.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Formats the error message to be more readable.
     *
     * @return A readable error string.
     */
    public String getPrettyErrorMsg() {
        return "[ERROR]: " + getMessage();
    }
}
