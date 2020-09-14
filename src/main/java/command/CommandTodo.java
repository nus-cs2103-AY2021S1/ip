package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskType;

/**
 * Todo Command
 *
 * creates a Task of type Todo and adds it to the taskList
 */

public class CommandTodo implements Command{
	private final String fullCommand;

	public CommandTodo(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		StringBuilder outputStringBuilder = new StringBuilder();
		assert fullCommand.length() <= 5 : "todo: string is too short.";
		String input = fullCommand.substring(4);
		String temp;
		if (input.isEmpty()) {
			temp = "\uD83D\uDE00 OOPS!!! The description of a todo cannot be empty.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		Task task = new Task(TaskType.TODO, false, input);
		taskList.add(task);

		temp = "Got it. I've added this task:";
		outputStringBuilder.append(temp).append("\n");

		temp = " " + task.getTypeString() + task.getDoneString() + input;
		outputStringBuilder.append(temp).append("\n");

		temp ="Now you have " + taskList.size() + " tasks in the list.";
		outputStringBuilder.append(temp);

		return outputStringBuilder.toString();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
