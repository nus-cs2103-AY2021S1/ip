package duke.exception;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a duke exception that is specific to Duke.
     * @param errorMessage the error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}