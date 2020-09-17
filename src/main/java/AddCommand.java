/**
 * Encapsulates an add command with a Task object.
 */
public class AddCommand extends Command {
    /**
     * Represents the task which will be added into the task list.
     */
    public Task task;

    /**
     * Instantiates an AddCommand object.
     * @param task the task to be added into the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task into the task list 
     * and updates the storage with the new task.
     * @param tasks the task list which the task is to be added into.
     * @param ui the ui used to display the added task.
     * @param storage the storage used to store the new task.
     * @return a string representation of the task to be added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showAddedTask(task, tasks.taskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
