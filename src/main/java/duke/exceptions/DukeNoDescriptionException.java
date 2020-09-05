package duke.exceptions;


/**
 * Duke No Description Exception class.
 * Thrown when no description is given.
 */
public class DukeNoDescriptionException extends DukeInvalidDescriptionException {

    /**
     *Creates a new DukeNoDescriptionException.
     */
    public DukeNoDescriptionException() {
        super();
    }


    /**
     *Creates a new DukeNoDescriptionException.
     *
     * @param errMsg error message.
     */
    public DukeNoDescriptionException(String errMsg) {
        super(errMsg);
    }

}
