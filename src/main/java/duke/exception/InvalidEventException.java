package duke.exception;

import static duke.util.Keyword.INVALID_EVENT_ERR_ONE;
import static duke.util.Keyword.INVALID_EVENT_ERR_THREE;
import static duke.util.Keyword.INVALID_EVENT_ERR_TWO;

import duke.ui.Ui;

/**
 * Thrown when the input to create an Event task is not formatted correctly.
 */
public class InvalidEventException extends DukeException {

    /**
     * Initializes the InvalidEventException object with the error message suggesting the proper format.
     */
    public InvalidEventException() {
        super(Ui.stringFormatter(INVALID_EVENT_ERR_ONE, INVALID_EVENT_ERR_TWO, INVALID_EVENT_ERR_THREE));
    }
}
