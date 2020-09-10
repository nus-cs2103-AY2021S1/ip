package raythx.grandma.command;

import raythx.grandma.storage.Storage;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

/**
 * Represents a List Command.
 */
public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.appendMessage("Here yur tasks faggit: \n" + tasks.toString());
    }
}
