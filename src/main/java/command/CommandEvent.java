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
		assert fullCommand.length() <= 6 : "event: string is too short.";
		String input = fullCommand.substring(5);
		StringBuilder outputStringBuilder = new StringBuilder();
		String temp;
		try {
			String[] inputAndTag = Task.getTagFromString(input);
			input = inputAndTag[0];
			String tag = inputAndTag[1];

			if (input.isEmpty()) {
				outputStringBuilder.append("☹ OOPS!!! The description of a event cannot be empty.");
				return outputStringBuilder.toString();
			}

			int index = input.indexOf("/at ");
			if (index == -1) {
				outputStringBuilder.append("☹ OOPS!!! The description of an event must have a indicated deadline.");
				return outputStringBuilder.toString();
			}

			StringBuilder taskStringBuilder = new StringBuilder();
			taskStringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(") ");
			input = taskStringBuilder.toString();
			Task task = new Task(TaskType.EVENT, false, input, tag);
			taskList.add(task);

			outputStringBuilder.append("Got it. I've added this task:").append("\n");

			outputStringBuilder.append(" ").append(task.toFullOutputString()).append("\n");

			temp = "Now you have " + taskList.size() + " tasks in the list.";
			outputStringBuilder.append("Now you have ").append(taskList.size()).append(" tasks in the list.");

			return outputStringBuilder.toString();
		} catch (duke.exception.DukeException e) {
			return outputStringBuilder.append(e.getMessage()).toString();
		}
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
