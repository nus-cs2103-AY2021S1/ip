package duke.exception;

/**
 * A special exception class that Duke could throw.
 */
public class DukeException extends Exception {
    private String msg;

    /**
     * Constructs a new Duke exception with the error message.
     *
     * @param msg the error message of this exception.
     */
    public DukeException(String msg) {
        this.msg = msg;
    }

    /**
     * Returns the message of this Duke exception.
     * @return the error message.
     */
    public String getMsg() {
        return this.msg;
    }
}
