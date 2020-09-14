package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Done Command
 *
 * Marks a task as done.
 * The task is specified by its index number.
 */

public class CommandDone implements Command {
	private final String fullCommand;

	public CommandDone(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String input = fullCommand.substring(4);
		StringBuilder outputStringBuilder = new StringBuilder();
		String temp;
		if(input.isEmpty()){
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
		if( (tasknumber == 0) ||(tasknumber >taskList.size()) ){
			temp = "☹ OOPS!!! Task number is not found in the list.";
			outputStringBuilder.append(temp);
			return outputStringBuilder.toString();
		}

		Task task = taskList.remove(tasknumber - 1);
		Task newTask = task.done();
		taskList.add(tasknumber - 1, newTask);


		temp = "Nice! I've marked this task as done:";
		outputStringBuilder.append(temp).append("\n");

		temp = " " + newTask.getTypeString() + newTask.getDoneString() + newTask.toString();
		outputStringBuilder.append(temp);


		return outputStringBuilder.toString();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
