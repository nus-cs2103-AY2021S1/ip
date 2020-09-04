package raythx98.grandma.command;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

public class DeleteCommand extends Command {
    private final int deleteIndex;
    public DeleteCommand (int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.addShowOnScreen("okcan deleted:\n    " + tasks.removeTask(deleteIndex)
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
