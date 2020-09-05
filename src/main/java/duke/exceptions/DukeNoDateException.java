package duke.exceptions;


/**
 * Duke No Date Exception class.
 * Thrown when description is missing a date.
 */
public class DukeNoDateException extends DukeInvalidDescriptionException {

    /**
     * Creates a new DukeNoDateException.
     */
    public DukeNoDateException() {
        super();
    }


    /**
     * Creates a new DukeNoDateException.
     *
     * @param errMsg error message.
     */
    public DukeNoDateException(String errMsg) {
        super(errMsg);
    }

}
