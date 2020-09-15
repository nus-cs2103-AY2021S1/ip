package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the input to create a Deadline task is not formatted correctly.
 */
public class InvalidDeadlineException extends DukeException {

    private static final String LINE_1 = "Deadline task is poorly formatted.";
    private static final String LINE_2 = "Here is a proper format: deadline 'task name' /by d-M-yy";
    private static final String LINE_3 = "e.g. deadline Exercise /by 23-8-20";

    /**
     * Initializes the InvalidDeadlineException object with the error message suggesting the proper format.
     */
    public InvalidDeadlineException() {
        super(Ui.stringFormatter(LINE_1, LINE_2, LINE_3));
    }
}
