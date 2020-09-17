package duke.exception;

/**
 * Represents an exception pertaining to Duke's operations.
 */
public class DukeException extends Exception {

    /**
     * Creates and initialises a new DukeException with a specified error message.
     *
     * @param message String containing the specified error message.
     */
    public DukeException(String message) {
        super("Oh no! " + message);
    }
}
