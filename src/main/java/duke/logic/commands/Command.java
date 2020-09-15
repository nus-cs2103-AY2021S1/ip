package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class for the different Duke commands.
 */
public abstract class Command {
    protected String command;

    /**
     * Constructor for Commands.
     *
     * @param command String input by user.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Abstract method for executing user commands.
     *
     * @param tm TaskManager that handles tasks in memory.
     * @param ui User interface that interacts with the user.
     * @param storage Storage class that handles saving and loading from file.
     * @throws DukeException If command is not properly formatted.
     */
    public abstract String execute(TaskManager tm, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns the exit signal for Duke to terminate.
     *
     * @return False for all Commands except ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
