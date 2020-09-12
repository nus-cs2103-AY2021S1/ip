public class DeleteCommand extends Command{
    protected int indexOfTaskToDelete;
    
    DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }
    
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(indexOfTaskToDelete);
        tasks.delete(indexOfTaskToDelete);
        String output = ui.showDeleted(taskToDelete, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }

    @Override
    boolean isExit() {
        return false;
    }
}
