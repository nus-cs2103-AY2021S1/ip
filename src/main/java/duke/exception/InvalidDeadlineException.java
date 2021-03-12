package duke.exception;

import static duke.util.Keyword.INVALID_DEADLINE_ERR_ONE;
import static duke.util.Keyword.INVALID_DEADLINE_ERR_THREE;
import static duke.util.Keyword.INVALID_DEADLINE_ERR_TWO;

import duke.ui.Ui;

/**
 * Thrown when the input to create a Deadline task is not formatted correctly.
 */
public class InvalidDeadlineException extends DukeException {

    /**
     * Initializes the InvalidDeadlineException object with the error message suggesting the proper format.
     */
    public InvalidDeadlineException() {
        super(Ui.stringFormatter(INVALID_DEADLINE_ERR_ONE, INVALID_DEADLINE_ERR_TWO, INVALID_DEADLINE_ERR_THREE));
    }
}
