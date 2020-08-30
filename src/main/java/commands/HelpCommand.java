package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * Represents an instruction from the user to display the help message.
 */
public class HelpCommand extends Command {

    /**
     * Executes the command to print the help message.
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        // ui.printHelp();
        ui.setMessageHelp();
    }
}
