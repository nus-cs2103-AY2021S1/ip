package command;

import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents the help command. The help command shows the user a list of commands Serina can help the user with and
 * how to issue these commands.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command. The execution involves listing all the commands Serina can understand.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     */
    public void execute(TaskList lst, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
