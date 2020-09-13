package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the task number provided by the user is not valid.
 */
public class InvalidTaskNumberException extends DukeException {

    private static final String LINE_1 = "Task number does not exist in the list.";
    private static final String LINE_2 = "Your current list only has %d tasks!";

    /**
     * Initializes the InvalidTaskNumberException object with the error message suggesting the proper format.
     *
     * @param size Size provided by the user.
     */
    public InvalidTaskNumberException(int size) {
        super(Ui.stringFormatter(LINE_1, String.format(LINE_2, size)));
    }
}
