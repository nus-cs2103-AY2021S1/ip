package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a help command which provides the user a help page that contains
 * a list of commands that the user can use.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command, which brings up a help page that contains
     * a list of commands that the user can use.
     *
     * @param tasks   the current TaskList object being used
     * @param ui      the current Ui object being used
     * @param storage the current Storage object being used
     * @return the String representation to display
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayOnHelp();
    }
}
