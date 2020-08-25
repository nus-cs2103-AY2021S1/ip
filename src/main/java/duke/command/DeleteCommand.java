package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
	int indexOfTask;

	/**
	 * Initializes the command with the index of the task to delete.
	 *
	 * @param indexOfTask The index of the task to delete.
	 */
	public DeleteCommand(int indexOfTask) {
		this.indexOfTask = indexOfTask;
	}

	/**
	 * Deletes the task in the TaskList, prints a message in the Ui, and updates the Storage.
	 *
	 * @param taskList The TaskList used by Duke.
	 * @param ui The Ui used by Duke.
	 * @param storage The Storage used by Duke.
	 * @throws DukeException If fail to delete or to update storage.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		try {
			Task taskToDelete = taskList.getTask(indexOfTask);
			taskList.deleteTask(indexOfTask);
			storage.updateStorage(taskList);
			ui.printMessage("Noted. I've removed this task.\n"
					+ taskToDelete.toString() + "\n"
						+ "Now you have " + taskList.numberOfTasks() + " tasks in the list.");
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Oops! Sorry, I couldn't find the task.");
		}
	}
}
