package raythx.grandma.command;

import raythx.grandma.storage.Storage;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

/**
 * Represents a Help Command.
 */
public class HelpCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.appendHelpMessage();
    }
}
