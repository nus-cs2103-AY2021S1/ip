package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Task.Task;

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
