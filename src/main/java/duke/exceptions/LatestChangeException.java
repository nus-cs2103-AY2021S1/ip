package duke.exceptions;

/**
 * Exception thrown when trying to execute the undo command without any commands available to undo.
 */
public class LatestChangeException extends DukeException {

    /**
     * Constructs a new LatestChangeException.
     */
    public LatestChangeException() {
        super("No commands available to undo",
                "OOPS! No commands available to undo!");
    }
}
