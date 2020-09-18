/**
 * Encapsulates a DoneCommand with an index
 * which specifies the specific task as done.
 */
public class DoneCommand extends Command {
    /**
     * Represents the index of the task to be marked as done.
     */
    public int index;
    
    /**
     * Instantiates a DoneCommand object.
     * @param index the index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as done, updates the 
     * task list with the task that is marked as done 
     * and displays the UI for the done task.
     * @param tasks the task list which the task is to be marked as done from.
     * @param ui the ui used to display the done task.
     * @param storage the storage used to store the updated task list.
     * @return a string representation of the task to be marked as done.
     * @throws DukeException throws an exception when saving
     * the task list into the storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.doneTask(index);
        storage.save(tasks);
        return ui.showDone(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
