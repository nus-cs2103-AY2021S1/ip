package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Delete Command
 *
 * Removes the task in the TaskList.
 * The task is specified by their corresponding index number.
 */

public class CommandDelete implements Command {
	private final String fullCommand;

	public CommandDelete(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		assert fullCommand.length() <= 7 : "delete: string is too short.";
		String input = fullCommand.substring(6);
		StringBuilder outputStringBuilder = new StringBuilder();
		String temp;
		if(input.isEmpty()) {
			temp = "☹ OOPS!!! Please indicate which task is done.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		input = input.substring(1);
		boolean isInteger = true;
		for(int charIndex = 0; charIndex < input.length(); charIndex++){
			if(!Character.isDigit(input.charAt(charIndex))){
				isInteger = false;
				break;
			}
		}
		if(!isInteger) {
			temp = "☹ OOPS!!! Incorrect entry for finished task.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		int tasknumber = Integer.parseInt(input);
		if( (tasknumber == 0) ||(tasknumber > taskList.size()) ){
			temp = "☹ OOPS!!! Task number is not found in the list.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		Task task = taskList.remove(tasknumber - 1);

		temp = "Noted. I've removed this task:";
		outputStringBuilder.append(temp).append("\n");

		temp = " " + task.getTypeString() + task.getDoneString() + task.toString();
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
