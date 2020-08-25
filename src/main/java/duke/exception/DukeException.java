package duke.exception;

/**
 * Throws when there is any unexpected behavior in the execution of Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates a <code>DukeException</code> object.
     * @param message The message of the description
     */
    public DukeException(String message) {
        super(message);
    }
}