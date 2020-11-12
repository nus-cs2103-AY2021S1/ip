package duke.response.exception;

import duke.common.exception.DukeException;

public class DukeInputException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeInputException(String message) {
        super(message);
    }
}
