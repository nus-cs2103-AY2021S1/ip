package duke.exception;

/**
 * Exception Class that is thrown when invalid inputs are given by the user.
 */
public class DukeException extends Exception {
    /**
     * Creates a new exception instance.
     *
     * @param errMessage Displays error message to user.
     */
    public DukeException(String errMessage) {
        super(errMessage);
    }

}