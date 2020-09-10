public class UpdateDescriptionCommand extends UpdateCommand {
    final String newDescription;
    
    public UpdateDescriptionCommand(int indexOfTaskToUpdate, String newDescription) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
    }
    
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateDescription(newDescription);
        
        storage.save(tasks);
        
        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
