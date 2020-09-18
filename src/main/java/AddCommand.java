/**
 * Encapsulates an add command with a task.
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
     * Adds a task into the task list, updates the storage 
     * with the new task and displays the ui for the added task.
     * @param tasks the task list which the task is to be added into.
     * @param ui the ui used to display the added task.
     * @param storage the storage used to store the updated task list.
     * @return a string representation of the task to be added.
     * @throws DukeException throws an exception when saving 
     * the task list into the storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showAddedTask(task, tasks.taskListLength());
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
