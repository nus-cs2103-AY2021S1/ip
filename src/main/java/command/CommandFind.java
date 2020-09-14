package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Find Command
 *
 * Search for task in tasklist and outputs the task and its number.
 * Tasks displayed contains the phrase used while describing the command.
 */
public class CommandFind implements Command {
	private final String fullCommand;

	public CommandFind(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		assert fullCommand.length() <= 6 : "find: string is too short.";
		String phrase = fullCommand.substring(5);
		StringBuilder outputStringBuilder = new StringBuilder();
		String temp = "Here are the matching tasks in your list:";
		outputStringBuilder.append(temp).append("\n");
		for (int taskListIndex = 1; taskListIndex <= taskList.size(); taskListIndex++) {
			duke.task.Task task = taskList.get(taskListIndex -1);
			if (task.getFullString().contains(phrase)) {
				temp = taskListIndex + "." + task.getFullString();
				outputStringBuilder.append(temp).append("\n");
			}
		}
		return outputStringBuilder.deleteCharAt(outputStringBuilder.length()-1).toString();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
