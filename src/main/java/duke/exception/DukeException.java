package duke.exception;

/**
 * Exception class to handles user input exceptions.
 */
public class DukeException extends Exception {

    /**
     * Constructor
     * @param message the error message to show
     */
    public DukeException(String message) {
        super(message);
    }
}
