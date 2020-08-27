package duke;

/**
 * Encapsulates the exception when a parser receives an invalid input.
 */
public class DukeException extends Exception {

    /**
     * Creates an exception for Duke-related errors.
     *
     * @param message Message from Duke.
     */
    public DukeException(String message) {
        super(message);
    }
}
