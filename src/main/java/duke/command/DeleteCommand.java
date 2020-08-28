package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {


	private final String markItem;

	public DeleteCommand(String secondArg) {
		markItem = secondArg;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

		//check if second argument is integer & valid
		try {
			if ((Integer.parseInt(this.markItem) < 1) || (Integer.parseInt(this.markItem) > tasks.getSize())) {
				throw new DukeException("Please enter a valid item number from the list!");
			}
		}

		//second argument wrong format
		catch (NumberFormatException e) {
			throw new DukeException("Please only input 'delete <item number>' with no other inputs!");
		}
		int deleteIndex = Integer.parseInt(this.markItem);

		//delete task
		Task deletedItem = tasks.delete(deleteIndex);

		//print output
		ui.printTaskDeleted(tasks, deletedItem);

		//update storage
		storage.saveListToHardDisk(tasks);
	}

	@Override
	public boolean isExit() {
		return false;
	}
}


