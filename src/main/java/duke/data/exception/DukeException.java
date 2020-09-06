package duke.data.exception;

/**
 * Base class for custom exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for custom Exception.
     * @param message Error message explaining the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
