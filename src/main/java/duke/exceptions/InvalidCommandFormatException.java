package duke.exceptions;

import static duke.utils.Messages.MESSAGE_INVALID_COMMAND_FORMAT_EXCEPTION;

/**
 * Thrown when the user inputs an invalid command format.
 */
public class InvalidCommandFormatException extends DukeException {

    /**
     * Constructor.
     */
    public InvalidCommandFormatException() {
        super(MESSAGE_INVALID_COMMAND_FORMAT_EXCEPTION);
    }
}
