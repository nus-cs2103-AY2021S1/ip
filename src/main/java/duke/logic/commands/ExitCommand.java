package duke.logic.commands;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command by the user to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by showing the exit message to the user.
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";

        return ui.showExit();
    }

    /**
     * Returns true as the command is an exit command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
