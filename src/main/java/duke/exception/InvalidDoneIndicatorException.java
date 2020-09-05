package duke.exception;

/**
 * Represents an exception when user does not provide any keyword/keyphrase.
 */
public class InvalidDoneIndicatorException extends DukeException {

    /**
     * Constructs a InvalidDoneIndicatorException.
     */
    public InvalidDoneIndicatorException() {
        super("OOPS!!! Cannot convert to true or false.");
    }
}
