/**
 * Represents a delete command for adding a task.
 */
public class DeleteCommand extends Command {

    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command.
     * @param tasks list of tasks.
     * @param ui user interface to display deleted message.
     * @param storage file storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.getTask(taskNumber);
        tasks.delete(taskNumber);
        return ui.showDeletedMessage(currTask, tasks.getTotalTasks());
    }
}
