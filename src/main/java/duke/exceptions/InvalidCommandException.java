package duke.exceptions;

import static duke.utils.Messages.MESSAGE_INVALID_COMMAND;

/**
 * Thrown to indicate that the user input an invalid command.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs the InvalidCommandException with the relevant detail message.
     */
    public InvalidCommandException() {
        super(MESSAGE_INVALID_COMMAND);
    }
}
