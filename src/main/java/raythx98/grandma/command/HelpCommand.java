package raythx98.grandma.command;

import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

/**
 * Represents a Help Command.
 */
public class HelpCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.appendHelpMessage();
    }
}
