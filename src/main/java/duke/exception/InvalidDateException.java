package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the {@code Deadline} task has the wrong date format.
 */
public class InvalidDateException extends DukeException {

    private static final String LINE_1 = "Date is poorly formatted!";
    private static final String LINE_2 = "Time should be in either of these 3 formats:";
    private static final String LINE_3 = "yyyy-MM-dd";
    private static final String LINE_4 = "yyyy-MM-dd hh:mm";
    private static final String LINE_5 = "hh:mm";

    /**
     * Initializes the {@code InvalidTimeException} object.
     */
    public InvalidDateException() {
        super(Ui.stringFormatter(LINE_1, LINE_2, LINE_3, LINE_4, LINE_5));
    }
}
