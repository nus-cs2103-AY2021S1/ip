/**
 * Represents an add command for adding a task.
 */
public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     * @param tasks list of tasks to which the task is to be added.
     * @param ui user interface to display added message.
     * @param storage file storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.containsTask(this.task)) {
            return ui.showDuplicateMessage();
        } else {
            tasks.add(this.task);
            return ui.showAddedMessage(this.task, tasks.getTotalTasks());
        }
    }
}
