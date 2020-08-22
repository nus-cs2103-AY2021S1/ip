package duke.exception;

/**
 * Represents a DukeException in which the command is invalid.
 */
public class DukeInvalidCommandException extends DukeException{
    /**
     * Constructor.
     * @param message string representing the exception
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
