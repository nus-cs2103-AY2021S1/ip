package duke.exception;

/**
 * Represents an exception when user does not input any content when adding Task.
 */
public class NoTaskContentException extends DukeException {

    /**
     * Constructs a NoTaskContentException.
     */
    public NoTaskContentException() {
        super("OOPS!!! The content of a task cannot be empty.");
    }
}
