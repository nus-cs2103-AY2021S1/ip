package duke.exceptions;

/**
 * Represents an exception for failing to run command.
 */
public class CommandException extends DukeException {
    private static final String ERROR_MSG = "Sorry! We cannot do so.";

    /**
     * Creates a {@code CommandException} with default error message.
     */
    public CommandException() {
        super(ERROR_MSG);
    }
    /**
     * Creates a {@code CommandException} with given error message.
     */
    public CommandException(String errMsg) {
        super(errMsg);
    }
}
