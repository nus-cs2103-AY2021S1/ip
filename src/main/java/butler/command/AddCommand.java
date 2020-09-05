package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.Task;
import butler.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        taskList.addTask(task);
        storage.storeTaskList(taskList);
        ui.showTaskAdded(task);
    }

    @Override
    public Boolean isExit() {
        return false;
    }

    public Task getTask() {
        return task;
    }
}
