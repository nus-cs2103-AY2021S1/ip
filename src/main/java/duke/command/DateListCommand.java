package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class DateListCommand extends Command {
	LocalDate localDate;

	/**
	 * Initializes the command with the LocalDate to match with the tasks in TaskList.
	 *
	 * @param localDate
	 */
	public DateListCommand(LocalDate localDate) {
		this.localDate = localDate;
	}

	/**
	 * Searches for tasks that have the same date and time as localDate
	 * and prints them in the Ui.
	 *
	 * @param taskList The TaskList used by Duke.
	 * @param ui The Ui used by Duke.
	 * @param storage The Storage used by Duke.
	 * @throws DukeException
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		StringBuilder stringBuilder = new StringBuilder();
		int numberOfTasksFound = 0;
		stringBuilder.append("Here are the tasks with the date: " + localDate.toString() + "\n");

		for (int i = 0; i < taskList.numberOfTasks(); i++) {
			Task currentTask = taskList.getTask(i);
			if (currentTask instanceof Deadline) {
				Deadline deadline = (Deadline) currentTask;
				if (deadline.getLocalDate().equals(localDate)) {
					stringBuilder.append((i + 1) + ". " + deadline.toString() + "\n");
					numberOfTasksFound++;
				}
			} else if (currentTask instanceof Event) {
				Event event = (Event) currentTask;
				if (event.getLocalDate().equals(localDate)) {
					stringBuilder.append((i + 1) + ". " + event.toString() + "\n");
					numberOfTasksFound++;
				}
			}
		}
		if (numberOfTasksFound > 0) {
			ui.printMessage(stringBuilder.toString());
		} else {
			ui.printMessage("There are no tasks with the date: " + localDate.toString());
		}
	}

}
