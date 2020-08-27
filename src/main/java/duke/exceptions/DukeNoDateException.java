package duke.exceptions;


public class DukeNoDateException extends DukeInvalidDescriptionException {

    /**
     * Constructor for DukeNoDateException.
     *
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
