package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * List Command
 *
 * lists all the Tasks in TaskList.
 */

public class CommandList implements Command{
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		Task task;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Here are the tasks in your list:").append("\n");
		for (int i = 1; i <= taskList.size(); i++) {
			task = taskList.get(i - 1);
			stringBuilder.append(i).append(". ").append(task.toFullOutputString()).append("\n");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder.toString();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
