package duke.exception;

/**
 * Represents the exception when the command given is invalid.
 */
public class DukeInvalidCommandException extends DukeException {

    private static final String ERROR_MESSAGE = "Sorry! I don't know what %s means...\n";

    public DukeInvalidCommandException(String command) {
        super(String.format(ERROR_MESSAGE, command));
    }
}
