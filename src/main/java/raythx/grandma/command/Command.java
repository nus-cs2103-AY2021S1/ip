package raythx.grandma.command;

import raythx.grandma.exception.DukeException;
import raythx.grandma.storage.Storage;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

/**
 * Represents an abstract Command from which other Commands inherit.
 */
public abstract class Command {

    /**
     * Executes the given command
     *
     * @param tasks tasks.
     * @param ui the ui.
     * @param storage the storage.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
