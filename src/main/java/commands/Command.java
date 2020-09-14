package commands;

import data.exception.DukeException;
import ui.Ui;

/**
 * Base class of a Command in model.Duke.
 */

public abstract class Command {

    protected Ui ui;

    public Command (Ui ui) {
        this.ui = ui;
    }
    /**
     * Executes the given command.
     * @throws DukeException when a certain constraint has not been met.
     */
    public abstract String execute() throws DukeException;
}
