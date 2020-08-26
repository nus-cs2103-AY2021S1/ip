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
     * @param tasks list of tasks to which the task is to be added
     * @param ui user interface to display added message
     * @param storage file storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddedMessage(this.task, tasks.getTotalTasks());
    }
}
