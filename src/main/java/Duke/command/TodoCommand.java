package Duke.command;

import Duke.storage.Storage;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.task.Todo;
import Duke.ui.Ui;

/**
 * Represents a Command to add a new Todo object to the TaskList
 */
public class TodoCommand extends Command {

	private String description;

	/**
	 * Constructor takes in a String description.
	 *
	 * @param tsk String description of the Todo Task object.
	 */
	public TodoCommand(String tsk) {
		this.description = tsk;
	}

	/**
	 * Get the description of the task.
	 *
	 * @return String description of the task.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Creates a new Todo Task and adds it into the TaskList. The Storage
	 * is updated with the latest Task and the Ui Object will print out a
	 * relevant message to notify the user on this addition.
	 *
	 * @param tasks TaskList object containing the list of tasks.
	 * @param ui Ui object to output messages to the user.
	 * @param storage Storage object to interact and manipulate data from the hard disk.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task todo = new Todo(this.description);
		tasks.add(todo);

		//print output
		ui.printTaskAdded(tasks, todo);

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
