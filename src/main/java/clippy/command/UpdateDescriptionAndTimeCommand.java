package clippy.command;

import clippy.exception.UpdateToDoTimeException;
import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class UpdateDescriptionAndTimeCommand extends UpdateCommand {
    private String newDescription;
    private String newTime;

    public UpdateDescriptionAndTimeCommand(int indexOfTaskToUpdate, String newDescription, String newTime) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
        this.newTime = newTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UpdateToDoTimeException {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateTime(newTime);
        taskToUpdate.updateDescription(newDescription);
        
        storage.save(tasks);

        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
