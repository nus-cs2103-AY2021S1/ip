package duke.exceptions;

/**
 * Represents an exception for invalid command.
 */
public class InvalidCommandException extends InvalidInputException {
    private static final String ERROR_MSG = "Sorry! The command is not valid.";

    /**
     * Creates a {@code InvalidCommandException}.
     */
    public InvalidCommandException() {
        super(ERROR_MSG);
    }
}
