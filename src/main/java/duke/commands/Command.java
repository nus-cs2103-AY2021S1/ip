package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that is received from the user via the command line.
 */
public class Command {
    protected String commandDescription;
    private boolean isExit;

    /**
     * Creates an instance of a Command.
     *
     * @param commandDescription Description of the command body.
     * @param isExit Determines if the command causes Duke to exit.
     */
    public Command(String commandDescription, boolean isExit) {
        this.commandDescription = commandDescription;
        this.isExit = isExit;
    }

    /**
     * Returns whether the command will cause Duke to exit.
     *
     * @return true if command causes Duke to exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command and returns Duke's response.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showError("Can't execute generic command");
    }
}
