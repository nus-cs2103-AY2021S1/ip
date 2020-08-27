package duke.exceptions;

public class DukeException extends RuntimeException {

    /**
     * Constructor for DukeException.
     */
    public DukeException() {
        super();
    }


    /**
     * Constructor for DukeException.
     *
     * @param errMsg error message.
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }

}
