package Duke.command;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.Ui;

public class ListCommand extends Command {
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printList(tasks);
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
