package duke.exception;

public class DeadlineException extends DukeException {

    /**
     * Constructs the deadline exception for invalid details.
     *
     * @param msg Error message.
     */
    public DeadlineException(String msg) {
        super(msg);
    }
}
