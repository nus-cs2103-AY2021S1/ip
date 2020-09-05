package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        taskList.deleteTask(taskIndex);
        storage.storeTaskList(taskList);
        ui.showTaskDeleted(taskIndex);
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
