package duke.command.exception;

import duke.common.exception.DukeException;

/**
 * Represent an exception thrown when dealing with Command.
 */
public class DukeCommandException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeCommandException(String message) {
        super(message);
    }
}
