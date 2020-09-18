package clippy.command;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class DeleteCommand extends Command{
    private int indexOfTaskToDelete;
    
    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(indexOfTaskToDelete);
        tasks.delete(indexOfTaskToDelete);
        String output = ui.showDeleted(taskToDelete, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }

}
