package Duke.command;

import Duke.storage.Storage;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.Ui;

/**
 * Represents a Command to add a new Event object to the TaskList
 */
public class EventCommand extends Command {


	private String description;
	private String at;

	/**
	 * Constructor takes in a String description and a String time.
	 *
	 * @param description String description of the Event Task Object.
	 * @param at String time of the Event Task Object.
	 */
	public EventCommand(String description, String at) {
		this.description = description;
		this.at = at;
	}

	/**
	 * Creates a new Event Task and adds it into the TaskList. The Storage
	 * is updated with the latest Task and the Ui Object will print out a
	 * relevant message to notify the user on this addition.
	 *
	 * @param tasks TaskList object containing the list of tasks.
	 * @param ui Ui object to output messages to the user.
	 * @param storage Storage object to interact and manipulate data from the hard disk.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task event = new Event(this.description, this.at);
		tasks.add(event);

		//print output
		ui.printTaskAdded(tasks, event);

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
