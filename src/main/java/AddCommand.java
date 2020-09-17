/**
 * Encapsulates an AddCommand object, contains information about the task being added.
 */
public class AddCommand extends Command {

    /**
     * Represents the task being added to the task list.
     */
    private final Task task;
    
    AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the task list and updates the storage.
     * 
     * @param taskList taskList which the task is being added to.  
     * @param ui ui used to show the added task.
     * @param storage storage used to store the taskList.
     * @return a string representation of the added task.
     * @throws DukeException if updateTask fails.
     */
    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addNewTask(task);
        storage.updateTasks(taskList);
        return ui.showAddedTask(task, taskList.getListLength());
    }
}
