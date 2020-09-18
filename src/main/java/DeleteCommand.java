/**
 * Encapsulates a delete command with an index
 * which specifies the specific task to be deleted.
 */
public class DeleteCommand extends Command {
    /**
     * Represents the index of the task to be deleted.
     */
    public int index;

    /**
     * Instantiates a DeleteCommand object.
     * @param index the unique index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the specified task, updates the task list
     * in storage and displays the UI for the deleted task.
     * @param tasks the task list which the task is to be deleted from.
     * @param ui the ui used to display the deleted task.
     * @param storage the storage used to store the updated task list.
     * @return a string representation of the task to be deleted.
     * @throws DukeException throws an exception when 
     * saving the task list into storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return ui.showDeletedTask(deletedTask, tasks.taskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
