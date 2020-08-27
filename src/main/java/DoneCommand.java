/**
 * DoneCommand is a request to mark a Task as done.
 */

public class DoneCommand extends Command {

	private final int idx;

	public DoneCommand(int idx) {
		this.idx = idx;
	}

	/**
	 * Marks a task from the TaskList as done, alerts user that a task is marked as done, updates storage about
	 * finished task.
	 *
	 * @param tasks TaskList to be modified.
	 * @param ui Ui to be used to display feedback messages.
	 * @param storage Storage to be updated.
	 * @throws DukeException
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		tasks.markAsDone(idx);
		ui.showDoneTask(tasks.getTask(idx));
		storage.saveList(tasks);
	}
}

