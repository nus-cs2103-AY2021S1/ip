package duke.exceptions;

/**
 * Duke Invalid Description Exception class.
 * Thrown when description given is invalid.
 */
public class DukeInvalidDescriptionException extends DukeException {

    /**
     * Constructor for DukeInvalidDescriptionException.
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
