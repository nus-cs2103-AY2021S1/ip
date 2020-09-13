package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the user inputs a command which is not part of the known commands.
 */
public class UnknownCommandException extends DukeException {

    private static final String LINE_1 = "I'm sorry, but I don't know what that means.";
    private static final String LINE_2 = "Type 'help' to display the list of commands available.";

    /**
     * Initializes the UnknownCommandException object.
     */
    public UnknownCommandException() {
        super(Ui.stringFormatter(LINE_1, LINE_2));
    }
}