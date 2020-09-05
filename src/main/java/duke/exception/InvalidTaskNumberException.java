package duke.exception;

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
        super(Ui.stringFormatter("Task number does not exist in the list.",
            String.format("Your current list only has %d tasks!", size)));
    }
}
