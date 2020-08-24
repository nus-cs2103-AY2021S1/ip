package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {


	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		if (taskList.numberOfTasks() > 0) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Here are the tasks in your list:\n");

			for (int i = 0; i < taskList.numberOfTasks(); i++) {
				Task currentTask = taskList.getTask(i);
				stringBuilder.append((i + 1) + ". " + currentTask.toString() + "\n");
			}

			ui.printMessage(stringBuilder.toString());
		} else {
			ui.printMessage("There are no tasks yet!");
		}
	}
}
