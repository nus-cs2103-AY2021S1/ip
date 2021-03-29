package duke.exception;

/**
 * <code>Exception</code> thrown by Duke related to user input errors.
 */
public class DukeInputException extends DukeException {

    /**
     * Creates a new <code>DukeInputException</code> with the given error details.
     *
     * @param message Error details.
     */
    public DukeInputException(String message) {
        super(message);
    }

}
