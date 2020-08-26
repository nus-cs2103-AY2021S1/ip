package duke.exception;

/**
 * Exception that occurs when the user does not specify anything about the Deadline.
 */
public class DeadlineIncompleteException extends DukeException {

    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Please specify the description of a deadline.";
    }
}
