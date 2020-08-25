public class DeleteCommand extends Command {

	private final int idx;

	public DeleteCommand(int idx) {
		this.idx = idx;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		ui.formatDeletedTask(tasks.removeTask(idx), tasks);
		storage.saveList(tasks);
	}
}
