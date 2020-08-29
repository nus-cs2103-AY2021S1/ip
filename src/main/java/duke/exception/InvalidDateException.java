package duke.exception;

/** An exception when the date given is invalid. */
public class InvalidDateException extends DukeException {

    /** Constructs an InvalidDateException. */
    public InvalidDateException() {
        super("Invalid Date Format. Use YYYY-MM-DD");
    }

    /**
     * Constructs an InvalidDateException with the given message.
     *
     * @param msg The message given.
     */
    protected InvalidDateException(String msg) {
        super(msg);
    }
}
