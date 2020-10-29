package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a list command which allows users to view the
 * current list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, where the list of tasks will be printed by the ui.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayOnList(tasks);
    }

}
