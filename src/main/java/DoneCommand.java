public class DoneCommand extends Command {

	private final int idx;

	public DoneCommand(int idx) {
		this.idx = idx;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		tasks.markAsDone(idx);
		ui.formatDoneTask(tasks.getTask(idx));
		storage.saveList(tasks);
	}
}

