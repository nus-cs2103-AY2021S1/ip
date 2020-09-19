package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Wrapper Class to handle logic for all other Command types.
 */
public abstract class Command {
    protected boolean isExit;
    private int taskIndex;

    /**
     * Creates a Command to initialize the isExit and taskIndex properties.
     */
    public Command() {
        this.isExit = false;
        this.taskIndex = -1;
    }

    /**
     * Returns boolean value whether the command should terminate the bot.
     *
     * @return whether if command should terminate the bot.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command.
     *
     * @param ui Ui object to print messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of tasks.
     * @throws DukeException when there is a problem with the command execution.
     */
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException;

}
