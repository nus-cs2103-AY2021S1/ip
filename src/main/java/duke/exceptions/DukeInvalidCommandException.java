package duke.exceptions;


/**
 * Duke Invalid Command Exception class.
 * Thrown when command keyword is invalid.
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Creates a new DukeInvalidCommandException.
     */
    public DukeInvalidCommandException() {
        super();
    }


    /**
     * Creates a new DukeInvalidCommandException.
     *
     * @param errMsg error message.
     */
    public DukeInvalidCommandException(String errMsg) {
        super(errMsg);
    }

}
