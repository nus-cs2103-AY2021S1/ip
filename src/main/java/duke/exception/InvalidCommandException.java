package duke.exception;

/**
 * Represents the possible exceptions that can be thrown when the user input invalid commands.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Instantiates a new InvalidCommandException object.
     * @param errorMessage The detail error message.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
