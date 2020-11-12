package duke.response.exception;

import duke.command.exception.DukeCommandException;

/**
 * Represents an exception thrown when the Date or Time input is invalid.
 */
public class DukeInvalidDateTimeInputException extends DukeCommandException {
    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeInvalidDateTimeInputException(String message) {
        super(message);
    }
}
