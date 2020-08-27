package duke.exceptions;


public class DukeInvalidDescriptionException extends DukeException {

    /**
     * Constructor for DukeInvalidDescriptionException.
     *
     */
    public DukeInvalidDescriptionException() {
        super();
    }

    /**
     * Constructor for DukeInvalidDescriptionException.
     *
     * @param errMsg error message.
     */
    public DukeInvalidDescriptionException(String errMsg) {
        super(errMsg);
    }

}
