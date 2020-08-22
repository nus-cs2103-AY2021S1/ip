package duke.exception;

/**
 * Represents an exception associated with Duke-related operations.
 */
public class DukeException extends Exception {
    /**
     * Constructor.
     * @param message string representing the exception
     */
    DukeException(String message) {
        super(message);
    }
}
