package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * Represents an instruction from the user to quit the bot
 */
public class ExitCommand extends Command {

    /**
     * Prints the exit message
     * @param tasks The current TaskList
     * @param ui The Ui object in use
     * @param storage The Storage object in use
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }
}
