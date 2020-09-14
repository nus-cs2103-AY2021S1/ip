package duke.exception;

/**
 * Represents the exception that is thrown when duke is
 * unable to find matching description
 */
public class FindException extends DukeException {

    /**
     * Constructor of the FindException Class.
     *
     * @param errorMessage
     */
    public FindException(String errorMessage) {
        super(errorMessage);
    }
}
