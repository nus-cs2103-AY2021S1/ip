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
		String string = "Here are the tasks in your list:";
		StringBuilder stringBuilder = new StringBuilder().append(string).append("\n");
		for (int i = 1; i <= taskList.size(); i++) {
			task = taskList.get(i - 1);
			string = i + "." + task.getTypeString() + task.getDoneString() + task.getString();
			stringBuilder.append(string).append("\n");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder.toString();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
