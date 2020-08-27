package duke.exceptions;

/**
 * Duke Invalid Command Exception class.
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Constructor for DukeInvalidCommandException.
     */
    public DukeInvalidCommandException() {
        super();
    }


    /**
     * Constructor for DukeInvalidCommandException.
     *
     * @param errMsg error message.
     */
    public DukeInvalidCommandException(String errMsg) {
        super(errMsg);
    }

}
