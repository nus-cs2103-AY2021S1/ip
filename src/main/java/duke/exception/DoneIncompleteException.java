package duke.exception;

/**
 * Exception that occurs when the user does not specify which Task to be marked as done.
 */
public class DoneIncompleteException extends DukeException {

    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Please specify which task to be marked as done.";
    }
}
