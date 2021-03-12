package duke.exception;

import static duke.util.Keyword.INVALID_TASK_NUM_ONE;
import static duke.util.Keyword.INVALID_TASK_NUM_TWO;

import duke.ui.Ui;

/**
 * Thrown when the task number provided by the user is not valid.
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Initializes the InvalidTaskNumberException object with the error message suggesting the proper format.
     *
     * @param size Size provided by the user.
     */
    public InvalidTaskNumberException(int size) {
        super(Ui.stringFormatter(INVALID_TASK_NUM_ONE, String.format(INVALID_TASK_NUM_TWO, size)));
    }
}
