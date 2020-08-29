package duke.exception;

/**
 * A special exception class that Duke could throw.
 */
public class DukeException extends Exception{
    public String msg;

    /**
     * Constructs a new Duke exception with the error message.
     * @param msg the error message of this exception.
     */
    public DukeException(String msg) {
        this.msg = msg;
    }
}
