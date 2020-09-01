package raythx98.duke.command;

import raythx98.duke.exception.DukeException;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

/**
 * Represents an abstract Command from which other *Commands inherit.
 */
public abstract class Command {

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
