package Duke.command;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.Ui;

public class ExitCommand extends Command {

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printExit();
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
