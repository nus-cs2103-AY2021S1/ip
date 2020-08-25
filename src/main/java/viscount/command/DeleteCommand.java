package viscount.command;

import viscount.*;
import viscount.exception.ViscountException;
import viscount.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ViscountException {
        Task removedTask = tasks.remove(taskIndex);
        storage.saveToDisk(tasks.getTasks());
        ui.showDelete(removedTask, tasks.getTasksSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
