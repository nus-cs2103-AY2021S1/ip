package duke;

/**
 * Base class of exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException.
     *
     * @param message A custom message tagging the DukeException, detailing the error.
     */
    protected DukeException(String message) {
        super(message);
    }
}
