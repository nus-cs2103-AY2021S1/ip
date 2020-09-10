public class UpdateDescriptionAndTimeCommand extends UpdateCommand {
    String newDescription;
    String newTime;

    public UpdateDescriptionAndTimeCommand(int indexOfTaskToUpdate, String newDescription, String newTime) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
        this.newTime = newTime;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateDescription(newDescription);
        taskToUpdate.updateTime(newTime);

        storage.save(tasks);

        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
