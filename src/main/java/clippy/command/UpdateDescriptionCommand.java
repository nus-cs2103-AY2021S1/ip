package clippy.command;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class UpdateDescriptionCommand extends UpdateCommand {
    private String newDescription;
    
    public UpdateDescriptionCommand(int indexOfTaskToUpdate, String newDescription) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateDescription(newDescription);
        
        storage.save(tasks);
        
        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
