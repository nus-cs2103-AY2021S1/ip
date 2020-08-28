package command;

import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Abstraction of the command class.
 */
public abstract class Command {
    /**
     * Executes the logic of the command.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     */
    public abstract void execute(TaskList lst, Ui ui, Storage storage);
}
