package duke.exception;

import static duke.util.Keyword.OOPS_MESSAGE;

/**
 * Parent exception class for this program, which is thrown when there is an error in the user input.
 */
public class DukeException extends Exception {

    /**
     * Initializes the DukeException object.
     *
     * @param message Message to be displayed when exception is thrown.
     */
    protected DukeException(String message) {
        super(String.format(OOPS_MESSAGE, message));
    }
}
