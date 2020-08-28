package Duke.command;

import Duke.storage.Storage;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.Ui;

public class FindCommand extends Command{

	private String searchTerm;

	/**
	 * Takes in a search term to be used for filtering out matching task descriptions in a specified TaskList.
	 *
	 * @param searchTerm Search description for tasks.
	 */
	public FindCommand(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * Uses the searchTerm attribute to filter out tasks with descriptions containing the searchTerm. The tasks
	 * are added into a new TaskList and printed out by the Ui class to be shown to the user.
	 *
	 * @param tasks TaskList object containing the list of tasks.
	 * @param ui Ui object to output messages to the user.
	 * @param storage Storage object to interact and manipulate data from the hard disk.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {

		//Initialize new TaskList for matching tasks
		TaskList searchTasks = new TaskList();

		//loop through to find matching tasks to add to new TaskList
		for (Task task : tasks.getList()) {
			if (task.getDescription().contains(this.searchTerm)) {
				searchTasks.add(task);
			}
		}

		//print TaskList of matching tasks
		ui.printMatchingTasks();
		ui.printList(searchTasks);
	}

	/**
	 * Returns false to indicate that the Command does not exit the program.
	 *
	 * @return false indicator
	 */
	@Override
	public boolean isExit() {
		return false;
	}
}
