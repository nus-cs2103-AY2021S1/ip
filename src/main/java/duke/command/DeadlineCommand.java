package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to add a new Deadline object to the TaskList
 */
public class DeadlineCommand extends Command {

	private String description;
	private String by;

	/**
	 * Constructor takes in a String description and a String time.
	 *
	 * @param description String description of the Deadline Task Object.
	 * @param by String due time of the Deadline Task Object.
	 */
	public DeadlineCommand(String description, String by) {
		this.description = description;
		this.by = by;
	}

	/**
	 * Creates a new Deadline Task and adds it into the TaskList. The Storage
	 * is updated with the latest Task and the Ui Object will print out a
	 * relevant message to notify the user on this addition.
	 *
	 * @param tasks TaskList object containing the list of tasks.
	 * @param ui Ui object to output messages to the user.
	 * @param storage Storage object to interact and manipulate data from the hard disk.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task deadline = new Deadline(this.description, this.by);
		tasks.add(deadline);

		//print output
		ui.printTaskAdded(tasks, deadline);

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
