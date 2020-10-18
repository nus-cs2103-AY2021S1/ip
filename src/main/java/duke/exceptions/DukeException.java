package duke.exceptions;

/**
 * Represents a special exception for Duke project.
 */
public class DukeException extends Exception {
    /**
     * Creates a {@code DukeException} with error message.
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
