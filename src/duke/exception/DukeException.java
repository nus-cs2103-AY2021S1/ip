package duke.exception;

/**
 * Represents a DukeException thrown when something unexpected occurs in the program.
 */
public class DukeException extends Exception {

    /**
     * Creates a instance of a DukeException.
     *
     * @param message Message to display to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
