package duke.exception;

/**
 * Represents an exception when user does not input any content when adding tasks.
 */
public class NoTaskContentException extends DukeException{

    /**
     * Constructs a NoTaskContentException.
     *
     * @param errorMessage Error Message to show.
     */
    public NoTaskContentException(String errorMessage) {
        super(errorMessage);
    }
}
