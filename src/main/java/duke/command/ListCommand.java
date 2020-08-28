package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to list the tasks in the TaskList.
 */
public class ListCommand extends Command {

	/**
	 * Lists the tasks in the TaskList object.
	 *
	 * @param tasks TaskList object containing the list of tasks.
	 * @param ui Ui object to output messages to the user.
	 * @param storage Storage object to interact and manipulate data from the hard disk.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printList(tasks);
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
