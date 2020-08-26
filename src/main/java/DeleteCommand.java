/**
 * Encapsulates a DeleteCommand with an index
 * which specifies the specific task to be deleted.
 */
public class DeleteCommand extends Command {
    public int index;

    /**
     * Instantiates a DeleteCommand object.
     * @param index the unique index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the specified task and displays the UI for the deleted task.
     * @param tasks the current TaskList.
     * @param ui the current Ui.
     * @param storage the current Storage.
     * @throws DukeException throws an exception when the index is more than the length of the taskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.taskListLength()) {
            throw new DukeException("ERROR: Task does not exist");
        }
        Task deletedTask = tasks.deleteTask(index);
        ui.showDeletedTask(deletedTask, tasks.taskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
