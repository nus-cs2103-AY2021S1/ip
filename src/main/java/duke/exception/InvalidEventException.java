package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the input to create an Event task is not formatted correctly.
 */
public class InvalidEventException extends DukeException {

    private static final String LINE_1 = "Event task is poorly formatted.";
    private static final String LINE_2 = "Here is a proper format: event 'event name' /at 'start time to end time'";
    private static final String LINE_3 = "e.g. meeting /at Sunday 2 - 4pm";

    /**
     * Initializes the InvalidEventException object with the error message suggesting the proper format.
     */
    public InvalidEventException() {
        super(Ui.stringFormatter(LINE_1, LINE_2, LINE_3));
    }
}
