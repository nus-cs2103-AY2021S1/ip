package duke;

/**
 * Encapsulates the exception when a parser receives an invalid input.
 */
public class DukeInputException extends Exception {

    /**
     * Creates an exception for Duke-related errors.
     *
     * @param message Message from Duke.
     */
    public DukeInputException(String message) {
        super(message);
    }
}
