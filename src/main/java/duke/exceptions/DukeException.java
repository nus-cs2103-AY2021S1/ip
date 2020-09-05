package duke.exceptions;


/**
 * Duke Exception class.
 * Throws custom exception commands if Duke encounters an error during runtime.
 */
public class DukeException extends RuntimeException {

    /**
     * Creates a new DukeException.
     */
    public DukeException() {
        super();
    }


    /**
     * Creates a new DukeException.
     *
     * @param errMsg error message.
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }

}
