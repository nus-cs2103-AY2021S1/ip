package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DoneCommand extends Command {

    public DoneCommand(String attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task markedTask = taskList.markDone(Integer.parseInt(attributes));
        ui.writeDone(markedTask);
        storage.storeList(taskList.getList());
        return true;
    }
}
