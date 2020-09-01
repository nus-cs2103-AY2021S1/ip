package raythx98.duke.command;

import raythx98.duke.exception.DukeException;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

public class DoneCommand extends Command {
    private final int doneIndex;
    public DoneCommand (int doneIndex) {
        this.doneIndex = doneIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showOnScreen("okcan done:");
        ui.showOnScreen(tasks.getTask(doneIndex).markAsDone());
        ui.showOnScreen("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}