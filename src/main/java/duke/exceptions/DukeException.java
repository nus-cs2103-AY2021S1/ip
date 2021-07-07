package duke.exceptions;

/**
 * Encapsulates a DukeException class to handle errors and exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException object with the message given.
     *
     * @param errorMessage a string representing the error message to be passed to the object.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
