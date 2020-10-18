package duke.exceptions;

/**
 * Represents an exception occured during Duke chat bot operations.
 * @version 1.0
 */
public class DukeException extends Exception {
    /**
     * Creates a new DukeException with the given message.
     *
     * @param message A String representation of the given message.
     */
    public DukeException(String message) {
        super(message);
    }
}
