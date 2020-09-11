package duke.exception;

/**
 * Parent exception class for this program.
 */
public abstract class DukeException extends Exception {
    /**
     * Initializes the DukeException Object.
     *
     * @param message Message to be displayed to the user when an invalid input is keyed in.
     */
    public DukeException(String message) {
        super(message);
    }
}
