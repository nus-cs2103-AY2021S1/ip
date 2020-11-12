package duke.exception;

/**
 * Exception thrown when the task format is incorrect.
 */
public class InvalidTaskFormatException extends DukeException {

    /**
     * Creates a InvalidTaskFormatException object.
     */
    public InvalidTaskFormatException() {
        super("Sorry wrong task format. If you want to add deadline: deadline [description] /by [YYYY-MM-DD]. "
                + "If you want to add event: event [description] /at [YYYY-MM-DD HH:mm]");
    }
}
