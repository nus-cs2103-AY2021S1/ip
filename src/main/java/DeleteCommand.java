/**
 * DeleteCommand is a request to delete a Task.
 */

public class DeleteCommand extends Command {

	private final int idx;

	public DeleteCommand(int idx) {
		this.idx = idx;
	}

	/**
	 * Deletes a task from the TaskList, alerts user that a task is deleted, updates storage with deleted task.
	 *
	 * @param tasks TaskList to be modified.
	 * @param ui Ui to be used to display feedback messages.
	 * @param storage Storage to be updated.
	 * @throws DukeException
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		ui.showDeletedTask(tasks.removeTask(idx), tasks);
		storage.saveList(tasks);
	}
}
