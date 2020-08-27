package duke.exceptions;

/**
 * Duke No Description Exception class.
 * Thrown when no description is given.
 */
public class DukeNoDescriptionException extends DukeInvalidDescriptionException {

    /**
     * Constructor for DukeNoDescriptionException.
     */
    public DukeNoDescriptionException() {
        super();
    }


    /**
     * Constructor for DukeNoDescriptionException.
     *
     * @param errMsg error message.
     */
    public DukeNoDescriptionException(String errMsg) {
        super(errMsg);
    }

}
