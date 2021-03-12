package duke.exception;

import static duke.util.Keyword.UNKNOWN_COMMAND_ONE;
import static duke.util.Keyword.UNKNOWN_COMMAND_TWO;

import duke.ui.Ui;

/**
 * Thrown when the user inputs a command which is not part of the known commands.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Initializes the UnknownCommandException object.
     */
    public UnknownCommandException() {
        super(Ui.stringFormatter(UNKNOWN_COMMAND_ONE, UNKNOWN_COMMAND_TWO));
    }
}
