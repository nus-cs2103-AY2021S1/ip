package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * This abstract class is a generic class which all other
 * command classes inherit from.
 */
public abstract class Command {
    protected boolean exit;

    /**
     * @return Boolean representing whether the program should terminate after executing the command
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Performs the necessary actions associated with the command.
     * @param taskList The TaskList associated with the Command
     * @param ui The Ui associated with the Command
     * @param storage The Storage associated with the Command
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
