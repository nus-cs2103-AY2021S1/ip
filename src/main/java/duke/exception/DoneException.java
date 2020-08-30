package duke.exception;

public class DoneException extends DukeException {

    /**
     * Constructs the done exception for invalid details.
     *
     * @param msg Error message.
     */
    public DoneException(String msg) {
        super(msg);
    }
}
