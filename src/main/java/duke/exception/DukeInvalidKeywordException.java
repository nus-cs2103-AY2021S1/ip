package duke.exception;

public class DukeInvalidKeywordException extends DukeException {
    /**
     * Constructor.
     */
    public DukeInvalidKeywordException() {
        super("Please enter a valid keyword!");
    }
}
