public class UpdateDescriptionAndTimeCommand extends UpdateCommand {
    String newDescription;
    String newTime;

    public UpdateDescriptionAndTimeCommand(int indexOfTaskToUpdate, String newDescription, String newTime) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
        this.newTime = newTime;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws UpdateToDoTimeException {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateTime(newTime);
        taskToUpdate.updateDescription(newDescription);
        
        storage.save(tasks);

        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
