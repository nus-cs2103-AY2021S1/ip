package duke.main;

import duke.command.Response;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Represents the main control class to run Duke.
 */
public class Duke {
    private Ui ui;

    /**
     * Constructs a Duke program.
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * Gets the Ui object in Duke.
     *
     * @return the Ui object.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Returns the response after user input.
     *
     * @param input string of user input.
     * @return the response to the user input.
     */
    public Response getResponse(String input) throws DukeException {
        return ui.getCommand(input).process();
    }

}
