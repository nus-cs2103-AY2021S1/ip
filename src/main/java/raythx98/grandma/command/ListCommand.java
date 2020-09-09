package raythx98.grandma.command;

import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

/**
 * Represents a List Command.
 */
public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.appendMessage("Here yur tasks faggit: \n" + tasks.toString());
    }
}
