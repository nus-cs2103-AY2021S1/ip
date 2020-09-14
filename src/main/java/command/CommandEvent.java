package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskType;

/**
 * Event Command
 *
 * Creates a new Task of type event and adds it to taskList.
 */

public class CommandEvent implements Command {
	private final String fullCommand;

	public CommandEvent(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String input = fullCommand.substring(5);
		StringBuilder outputStringBuilder = new StringBuilder();
		String temp;
		if(input.isEmpty()){
			temp = "☹ OOPS!!! The description of a event cannot be empty.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		int index = input.indexOf("/at");
		if (index == -1) {
			temp ="☹ OOPS!!! The description of an event must have a indicated deadline.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		StringBuilder taskStringBuilder = new StringBuilder();
		taskStringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(")");
		input = taskStringBuilder.toString();
		Task task = new Task(TaskType.DEADLINE, false, input);
		taskList.add(task);

		temp = "Got it. I've added this task:";
		outputStringBuilder.append(temp).append("\n");

		temp = " " + task.getTypeString() + task.getDoneString() + input;
		outputStringBuilder.append(temp).append("\n");

		temp = "Now you have " + taskList.size() + " tasks in the list.";
		outputStringBuilder.append(temp);

		return outputStringBuilder.toString();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
