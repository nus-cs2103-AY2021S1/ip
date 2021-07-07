package duke.exception;

/**
 * Represents an exception when user does not input a valid
 * description and/or DateTime format when adding Task.
 */
public class InvalidTaskContentException extends DukeException {

    /**
     * Constructs a InvalidTaskContentException.
     */
    public InvalidTaskContentException(String errorMessage) {
        super(errorMessage);
    }
}

