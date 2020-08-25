package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
	int indexOfTask;

	/**
	 * Initializes the command with the index of task to set as done.
	 *
	 * @param indexOfTask Index of the task to set as done.
	 */
	public DoneCommand(int indexOfTask) {
		this.indexOfTask = indexOfTask;
	}

	/**
	 * Sets the task at the indexOfTask in the TaskList as done, prints a message in the Ui
	 * and updates the Storage.
	 *
	 * @param taskList The TaskList used by Duke.
	 * @param ui The Ui used by Duke.
	 * @param storage The Storage used by Duke.
	 * @throws DukeException If unable to find the task or update the storage.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		Task taskToSetDone = taskList.getTask(indexOfTask);
		taskList.setTaskDone(indexOfTask);
		storage.updateStorage(taskList);
		ui.printMessage("Nice! I've marked this task as done:\n" +
				taskToSetDone.toString());
	}
}
