package duke.exception;

/**
 * Represents the exceptions that Duke will print if invalid commands are input by the user.
 */
public class DukeException extends Exception {
    /**
     * Represents the error message of the exception.
     */
    private String msg;

    /**
     * Constructs a new DukeException.
     * @param msg the error message of the DukeException.
     */
    public DukeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
