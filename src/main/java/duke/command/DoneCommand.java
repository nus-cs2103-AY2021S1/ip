package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

	private final String markItem;

	public DoneCommand(String secondArg) {
		markItem = secondArg;
	}


	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

		//check if second argument is integer
		try {
			if ((Integer.parseInt(this.markItem) < 1) || (Integer.parseInt(this.markItem) > tasks.getSize())) {
				throw new DukeException("Please enter a valid item number from the list!");
			}
		}

		//second argument wrong format
		catch (NumberFormatException e) {
			throw new DukeException("Please only input 'done <item number>' with no other inputs!");
		}
		int doneIndex = Integer.parseInt(this.markItem);

		tasks.markDone(doneIndex);

		ui.printTaskDone(tasks.getList().get(doneIndex-1));

		//update storage
		storage.saveListToHardDisk(tasks);
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
