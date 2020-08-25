/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Initializes delete command with the index the user inputted.
     * @param index The index of the task which the user wants to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list and shows the task deleted to the user.
     * Saves the new task list to the disk as well.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskDeleted(tasks.deleteTask(index - 1), tasks.size());
        storage.saveTaskList(tasks);
    }
}
