package raythx.grandma.command;

import raythx.grandma.storage.Storage;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

/**
 * Represents a Delete Command.
 */
public class DeleteCommand extends Command {
    private final int deleteIndex;
    public DeleteCommand (int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.appendMessage("okcan deleted:\n    " + tasks.removeTask(deleteIndex)
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
