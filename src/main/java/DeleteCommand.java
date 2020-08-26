public class DeleteCommand extends Command {

    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.getTask(taskNumber);
        tasks.delete(taskNumber);
        ui.showDeletedMessage(currTask, tasks.getTotalTasks());
    }
}
