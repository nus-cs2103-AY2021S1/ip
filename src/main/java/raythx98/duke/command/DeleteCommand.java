package raythx98.duke.command;

import raythx98.duke.exception.DukeException;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int deleteIndex;
    public DeleteCommand (int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.addShowOnScreen("okcan done:\n" + tasks.removeTask(deleteIndex)
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
