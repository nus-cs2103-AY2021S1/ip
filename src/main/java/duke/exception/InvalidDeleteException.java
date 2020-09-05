package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the user inputs a wrong delete format.
 */
public class InvalidDeleteException extends DukeException {

    private static final String LINE_1 = "Delete format is invalid.";
    private static final String LINE_2 = "Please try again with a proper format like 'delete 3'";

    /**
     * Initializes the InvalidDeleteException object with the error message suggesting the proper format.
     */
    public InvalidDeleteException() {
        super(Ui.stringFormatter(LINE_1, LINE_2));
    }
}
