package nite.command;

import nite.exception.NiteException;
import nite.storage.Storage;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents an abstract Command from which other *Commands inherit.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     * @throws NiteException If command is invalid.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws NiteException;

    /**
     * Returns whether a Command is an ExitCommand.
     *
     * @return False by default as most Commands are not ExitCommands.
     */
    public boolean isExit() {
        return false;
    }
}
