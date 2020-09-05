package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the user inputs a wrong done format.
 */
public class InvalidDoneException extends DukeException {

    private static final String LINE_1 = "Done format is invalid.";
    private static final String LINE_2 = "Please try again with a proper format like 'done 3'";

    /**
     * Initializes the InvalidDoneException object with the error message suggesting the proper format.
     */
    public InvalidDoneException() {
        super(Ui.stringFormatter(LINE_1, LINE_2));
    }
}
