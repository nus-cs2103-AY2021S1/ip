package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
	Task taskToAdd;

	/**
	 * Initializes an AddCommand with the task to be added as the input.
	 *
	 * @param taskToAdd The task to be added.
	 */
	public AddCommand(Task taskToAdd) {
		this.taskToAdd = taskToAdd;
	}

	/**
	 * Adds the taskToAdd to the TaskList, prints a message in the Ui and updates the Storage.
	 *
	 * @param tasks The TaskList.
	 * @param ui The Ui.
	 * @param storage The Storage.
	 * @throws DukeException If storage could not be updated.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		tasks.addTask(taskToAdd);
		storage.updateStorage(tasks);
		ui.printMessage("Got it. I've added this task:\n" +
				taskToAdd.toString() + "\n" +
				"Now you have " + tasks.numberOfTasks() + " tasks in the list.");
	}
}
