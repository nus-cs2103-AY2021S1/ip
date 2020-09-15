package duke.exception;

import static duke.util.Keyword.DATE_INPUT_FORMAT;
import static duke.util.Keyword.DATE_TIME_INPUT_FORMAT;
import static duke.util.Keyword.TIME_INPUT_FORMAT;

import duke.ui.Ui;

/**
 * Thrown when the {@code Deadline} task has the wrong date format.
 */
public class InvalidDateException extends DukeException {

    private static final String LINE_1 = "Date is poorly formatted!";
    private static final String LINE_2 = "Time should be in either of these 3 formats:";

    /**
     * Initializes the {@code InvalidTimeException} object.
     */
    public InvalidDateException() {
        super(Ui.stringFormatter(LINE_1, LINE_2, DATE_TIME_INPUT_FORMAT, DATE_INPUT_FORMAT, TIME_INPUT_FORMAT));
    }
}
