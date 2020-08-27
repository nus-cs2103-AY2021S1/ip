package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class AddCommand extends Command {
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showAdded(task);
        ui.showNumberInList(taskList);
        storage.updateStorage(taskList);
    }
}
