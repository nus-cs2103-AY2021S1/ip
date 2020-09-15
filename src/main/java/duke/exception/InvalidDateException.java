package duke.exception;

import static duke.util.Keyword.DATE_INPUT_FORMAT;
import static duke.util.Keyword.DATE_TIME_INPUT_FORMAT;
import static duke.util.Keyword.INVALID_DATE_ERR_ONE;
import static duke.util.Keyword.INVALID_DATE_ERR_TWO;
import static duke.util.Keyword.TIME_INPUT_FORMAT;

import duke.ui.Ui;

/**
 * Thrown when the {@code Deadline} task has the wrong date format.
 */
public class InvalidDateException extends DukeException {

    /**
     * Initializes the {@code InvalidTimeException} object.
     */
    public InvalidDateException() {
        super(Ui.stringFormatter(INVALID_DATE_ERR_ONE, INVALID_DATE_ERR_TWO, DATE_TIME_INPUT_FORMAT,
            DATE_INPUT_FORMAT, TIME_INPUT_FORMAT));
    }
}
