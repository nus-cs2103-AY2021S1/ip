package duke.exceptions;


/**
 * Duke Invalid Description Exception class.
 * Thrown when description given is invalid.
 */
public class DukeInvalidDescriptionException extends DukeException {

    /**
     * Creates a new DukeInvalidDescriptionException.
     */
    public DukeInvalidDescriptionException() {
        super();
    }


    /**
     * Creates a new DukeInvalidDescriptionException.
     *
     * @param errMsg error message.
     */
    public DukeInvalidDescriptionException(String errMsg) {
        super(errMsg);
    }

}
