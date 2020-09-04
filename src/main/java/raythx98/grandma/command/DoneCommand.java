package raythx98.grandma.command;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

public class DoneCommand extends Command {
    private final int doneIndex;
    public DoneCommand (int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.addShowOnScreen("okcan mark completed:\n    " + tasks.getTask(doneIndex).markAsDone()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}