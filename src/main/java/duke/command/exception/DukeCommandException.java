package duke.command.exception;

import duke.common.exception.DukeException;

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
