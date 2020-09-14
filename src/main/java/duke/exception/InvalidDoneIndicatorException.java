package duke.exception;

/**
 * Represents an exception when the String is not '0' or '1' and thus cannot be convert to a boolean value.
 */
public class InvalidDoneIndicatorException extends DukeException {

    /**
     * Constructs a InvalidDoneIndicatorException.
     */
    public InvalidDoneIndicatorException() {
        super("OOPS!!! Cannot convert to true or false.");
    }
}
