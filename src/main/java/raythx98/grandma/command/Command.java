package raythx98.grandma.command;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

/**
 * Represents an abstract Command from which other *Commands inherit.
 */
public abstract class Command {

    /**
     * Something.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether a Command is an ExitCommand.
     *
     * @return False by default as most Commands are not ExitCommands.
     */
    public boolean isExit() {
        return false;
    }
}
