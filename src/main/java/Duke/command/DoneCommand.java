package Duke.command;

import Duke.exception.DukeException;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.Ui;

/**
 * Represents a Command to mark an existing object in the TaskList as done
 */
public class DoneCommand extends Command {

	private final String markItem;

	/**
	 * Constructor takes in a String value of the instruction to be further processed.
	 *
	 * @param secondArg String argument to specify the Task to delete.
	 */
	public DoneCommand(String secondArg) {
		markItem = secondArg;
	}

	/**
	 *  Processes the String attribute markItem to make sure that a valid Task number
	 *  is given. The Task will be marked as done from the TaskList if it is valid.
	 *  Otherwise a DukeException will be thrown. The Storage is updated with this deletion
	 *  and the Ui Object will print out a relevant message to notify the user on this
	 *  deletion or exception.
	 *
	 * @param tasks TaskList object containing the list of tasks.
	 * @param ui Ui object to output messages to the user.
	 * @param storage Storage object to interact and manipulate data from the hard disk.
	 * @throws DukeException If number is not a valid item number in the task list.
	 */
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

	/**
	 * Returns false to indicate that the Command does not exit the program.
	 *
	 * @return Exit program indicator
	 */
	@Override
	public boolean isExit() {
		return false;
	}
}
