package duke.command;

import duke.task.Task;
import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

public class AddCommand implements Command {
    private final Task targetTask;

    public AddCommand(Task task) {
        this.targetTask = task;
    }

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(targetTask);
        ui.showAddedNotification(targetTask,tasks);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
