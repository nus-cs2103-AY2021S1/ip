package clippy.command;

import clippy.exception.UpdateToDoTimeException;
import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class UpdateTimeCommand extends UpdateCommand {
    private String newTime;

    public UpdateTimeCommand(int indexOfTaskToUpdate, String newTime) {
        super(indexOfTaskToUpdate);
        this.newTime = newTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UpdateToDoTimeException {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateTime(newTime);

        storage.save(tasks);

        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
