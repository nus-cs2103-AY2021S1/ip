package duke.exceptions;


/**
 * Duke No Date Exception class.
 * Thrown when description is missing a date.
 */
public class DukeNoDateException extends DukeInvalidDescriptionException {

    /**
     * Constructor for DukeNoDateException.
     */
    public DukeNoDateException() {
        super();
    }


    /**
     * Constructor for DukeNoDateException.
     *
     * @param errMsg error message.
     */
    public DukeNoDateException(String errMsg) {
        super(errMsg);
    }

}
