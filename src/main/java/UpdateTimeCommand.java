public class UpdateTimeCommand extends UpdateCommand {
    String newTime;

    public UpdateTimeCommand(int indexOfTaskToUpdate, String newTime) {
        super(indexOfTaskToUpdate);
        this.newTime = newTime;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateTime(newTime);

        storage.save(tasks);

        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
