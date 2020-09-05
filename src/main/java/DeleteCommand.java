/**
 * DeleteCommand is a request to delete a Task.
 */

public class DeleteCommand extends Command {

    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes a task from the TaskList updates storage with deleted task.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be updated.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.saveList(tasks);
        return Ui.getDeletedTask(tasks.removeTask(idx), tasks);
    }
}
