package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskType;
import duke.exception.DukeException;

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

		try {
			String[] inputAndTag = Task.getTagFromString(input);
			input = inputAndTag[0];
			String tag = inputAndTag[1];

			if (input.isEmpty()) {
				outputStringBuilder.append("\uD83D\uDE00 OOPS!!! The description of a todo cannot be empty.");
				return outputStringBuilder.toString();
			}

			Task task = new Task(TaskType.TODO, false, input, tag);
			taskList.add(task);


			outputStringBuilder.append("Got it. I've added this task:").append("\n");

			outputStringBuilder.append(" ").append(task.toFullOutputString()).append("\n");

			outputStringBuilder.append("Now you have ").append(taskList.size()).append(" tasks in the list.");

			return outputStringBuilder.toString();
		} catch (DukeException e) {
			return outputStringBuilder.append(e.getMessage()).toString();
		}

	}

	@Override
	public boolean isExit() {
		return false;
	}
}
