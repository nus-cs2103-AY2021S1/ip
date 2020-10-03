package duke.exception;

/**
 * Exception class to handles user input exceptions.
 */
// Credits to @nicholas-gcc for structural idea behind exceptions
public class DukeException extends Exception {

    /**
     * Constructor
     * @param message the error message to show
     */
    public DukeException(String message) {
        super(message);
    }
}
