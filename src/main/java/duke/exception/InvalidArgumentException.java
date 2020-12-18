package duke.exception;

/**
 * Represents the possible exceptions that can be thrown when the arguments of a command are invalid.
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Instantiates a new InvalidArgumentException object.
     * @param errorMessage The detail error message.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
