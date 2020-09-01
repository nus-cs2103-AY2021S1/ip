package duke;

import duke.DukeException;
import duke.Command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Optional;

public class Parser {
	public static Command parse(String fullCommand) throws DukeException {
		if(fullCommand.equals("list")){
			return list();
		} else if (fullCommand.equals("bye")) {
			return bye();
		} else if (getWord(fullCommand).equals("done")) {
			return done(fullCommand);
		} else if (getWord(fullCommand).equals("todo")) {
			return todo(fullCommand);
		} else if (getWord(fullCommand).equals("deadline")) {
			return deadline(fullCommand);
		} else if (getWord(fullCommand).equals("event")) {
			return event(fullCommand);
		} else if (getWord(fullCommand).equals("delete")) {
			return delete(fullCommand);
		}
		else if(getWord(fullCommand).equals("save")){
			return save();
		} else if (getWord(fullCommand).equals("find")) {
			return find(fullCommand);
		}
		else{
			throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
	}
	public static String getWord(String string) {

		int firstSpaceIndex = string.indexOf(' ');
		if (firstSpaceIndex == -1) {
			return string;
		}
		return string.substring(0, firstSpaceIndex);
	}

	public static Command list() {
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				Task task;
				String string = "Here are the tasks in your list:";
				StringBuilder stringBuilder = new StringBuilder().append(string).append("\n");
				ui.showOutput(string);
				for (int i = 1; i <= taskList.size(); i++) {
					task = taskList.get(i - 1);
					string = i + "." + task.getTypeString() + task.getDoneString() + task.getString();
					stringBuilder.append(string).append("\n");
					ui.showOutput(string);
				}
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				return stringBuilder.toString();
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}

	public static Command bye() {
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String string = "Bye. Hope to see you again soon!";
				ui.showOutput(string);
				return string;
			}

			@Override
			public boolean isExit() {
				return true;
			}
		};
	}

	public static Command todo(String string) {
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				StringBuilder stringBuilder = new StringBuilder();
				String input = string.substring(4);
				String temp;
				if (input.isEmpty()) {
					temp = "☹ OOPS!!! The description of a todo cannot be empty.";
					stringBuilder.append(temp);
					ui.showOutput(temp);
				} else {
					duke.Task task = new duke.Task(duke.TaskType.TODO, false, input);
					taskList.add(task);

					temp = "Got it. I've added this task:";
					ui.showOutput(temp);
					stringBuilder.append(temp).append("\n");

					temp = " " + task.getTypeString() + task.getDoneString() + input;
					ui.showOutput(temp);
					stringBuilder.append(temp).append("\n");

					temp ="Now you have " + taskList.size() + " tasks in the list.";
					ui.showOutput(temp);
					stringBuilder.append(temp);
				}
				return stringBuilder.toString();
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}

	public static Command deadline(String string) {
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(8);
				String temp;
				StringBuilder outputStringBuilder = new StringBuilder();
				if (input.isEmpty()) {
					temp = "☹ OOPS!!! The description of a deadline cannot be empty.";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);
					return outputStringBuilder.toString();
				}
				int index = input.indexOf("/by");
				if (index == -1) {
					temp = "☹ OOPS!!! The description of a deadline must have a indicated deadline.";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);
					return outputStringBuilder.toString();
				}
				StringBuilder dateStringBuilder = new StringBuilder();
				String[] strings = new String[1];
				if(input.length() >= index + 4) {
					strings = input.substring(index + 4).split("/");
				}
				if( (strings.length >= 3) && (strings[2].length() >= 9) ) {
					if(strings[0].length() < 2){
						strings[0] = "0" + strings[0];
					}
					dateStringBuilder.append(strings[2].substring(0, 4))
							.append("-").append(strings[1])
							.append("-").append(strings[0])
							.append("T")
							.append(strings[2].substring(5, 7))
							.append(":")
							.append(strings[2].substring(7, 9));
				}
				try{
					LocalDateTime localDateTime = LocalDateTime.parse(dateStringBuilder.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
					StringBuilder taskStringBuilder = new StringBuilder();
					taskStringBuilder.append(input.substring(0, index))
							.append("(by: ")
							.append(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)))
							.append(")");
					input = taskStringBuilder.toString();
					duke.Task task = new duke.Task(duke.TaskType.DEADLINE, false, input, Optional.of(localDateTime));
					taskList.add(task);

					temp = "Got it. I've added this task:";
					ui.showOutput(temp);
					outputStringBuilder.append(temp).append("\n");

					temp = " " + task.getTypeString() + task.getDoneString() + input;
					ui.showOutput(temp);
					outputStringBuilder.append(temp).append("\n");

					temp = "Now you have " + taskList.size() + " tasks in the list.";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);

					return outputStringBuilder.toString();
				} catch(DateTimeParseException e) {
					temp = "☹ OOPS!!! The description of a deadline must have a valid date and time. (Format: /by dd/mm/yyyy tttt e.g 2/12/2019 1800";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);
					return outputStringBuilder.toString();
				}
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};

	}
	public static Command event(String string){
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(5);
				StringBuilder outputStringBuilder = new StringBuilder();
				String temp;
				if(input.isEmpty()){
					temp = "☹ OOPS!!! The description of a event cannot be empty.";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);
				}else {
					int index = input.indexOf("/at");
					if (index == -1) {
						temp ="☹ OOPS!!! The description of an event must have a indicated deadline.";
						ui.showOutput(temp);
						outputStringBuilder.append(temp);
					} else {
						StringBuilder taskStringBuilder = new StringBuilder();
						taskStringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(")");
						input = taskStringBuilder.toString();
						duke.Task task = new duke.Task(duke.TaskType.DEADLINE, false, input);
						taskList.add(task);

						temp = "Got it. I've added this task:";
						ui.showOutput(temp);
						outputStringBuilder.append(temp).append("\n");

						temp = " " + task.getTypeString() + task.getDoneString() + input;
						ui.showOutput(temp);
						outputStringBuilder.append(temp).append("\n");

						temp = "Now you have " + taskList.size() + " tasks in the list.";
						ui.showOutput(temp);
						outputStringBuilder.append(temp);
					}
				}
				return outputStringBuilder.toString();
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}

	public static Command done(String string){
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(4);
				StringBuilder outputStringBuilder = new StringBuilder();
				String temp;
				if(input.isEmpty()){
					temp = "☹ OOPS!!! Please indicate which task is done.";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);
				}else{
					input = input.substring(1);
					boolean isInteger = true;
					for(int i = 0; i < input.length(); i++){
						if(!Character.isDigit(input.charAt(i))){
							isInteger = false;
							break;
						}
					}
					if(isInteger){
						int tasknumber = Integer.parseInt(input);
						if( (tasknumber == 0) ||(tasknumber >taskList.size()) ){
							temp = "☹ OOPS!!! Task number is not found in the list.";
							ui.showOutput(temp);
							outputStringBuilder.append(temp);
						}
						else{
							duke.Task task =taskList.remove(tasknumber - 1);
							duke.Task newTask = new duke.Task(task.taskType, true, task.toString());
							taskList.add(tasknumber-1, newTask);

							temp = "Nice! I've marked this task as done:";
							ui.showOutput(temp);
							outputStringBuilder.append(temp).append("\n");

							temp = " " + newTask.getTypeString() + newTask.getDoneString() + newTask.toString();
							ui.showOutput(temp);
							outputStringBuilder.append(temp);
						}
					}
					else{
						temp = "☹ OOPS!!! Incorrect entry for finished task.";
						ui.showOutput(temp);
						outputStringBuilder.append(temp);
					}
				}
				return outputStringBuilder.toString();
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}

	public static Command delete(String string){
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(6);
				StringBuilder outputStringBuilder = new StringBuilder();
				String temp;
				if(input.isEmpty()){
					temp = "☹ OOPS!!! Please indicate which task is done.";
					ui.showOutput(temp);
					outputStringBuilder.append(temp);
				}else{
					input = input.substring(1);
					boolean isInteger = true;
					for(int i = 0; i < input.length(); i++){
						if(!Character.isDigit(input.charAt(i))){
							isInteger = false;
							break;
						}
					}
					if(isInteger){
						int tasknumber = Integer.parseInt(input);
						if( (tasknumber == 0) ||(tasknumber > taskList.size()) ){
							temp = "☹ OOPS!!! Task number is not found in the list.";
							ui.showOutput(temp);
							outputStringBuilder.append(temp);
						}
						else{
							duke.Task task = taskList.remove(tasknumber - 1);

							temp = "Noted. I've removed this task:";
							ui.showOutput(temp);
							outputStringBuilder.append(temp).append("\n");

							temp = " " + task.getTypeString() + task.getDoneString() + task.toString();
							ui.showOutput(temp);
							outputStringBuilder.append(temp).append("\n");

							temp = "Now you have " + taskList.size() + " tasks in the list.";
							ui.showOutput(temp);
							outputStringBuilder.append(temp);
						}
					}
					else{
						temp = "☹ OOPS!!! Incorrect entry for finished task.";
						ui.showOutput(temp);
						outputStringBuilder.append(temp);
					}
				}
				return outputStringBuilder.toString();
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}


	public static Command save() {
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String temp = storage.save(taskList);
				ui.showOutput(temp);
				return temp;
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}

	public static Command find(String fullCommand) {
		return new Command() {
			@Override
			public String execute(TaskList taskList, Ui ui, Storage storage) {
				String phrase = fullCommand.substring(5);
				StringBuilder outputStringBuilder = new StringBuilder();
				String temp = "Here are the matching tasks in your list:";
				ui.showOutput(temp);
				outputStringBuilder.append(temp).append("\n");
				for (int taskListIndex = 1; taskListIndex <= taskList.size(); taskListIndex++) {
					Task task = taskList.get(taskListIndex -1);
					if (task.getFullString().contains(phrase)) {
						temp = taskListIndex + "." + task.getFullString();
						ui.showOutput(temp);
						outputStringBuilder.append(temp).append("\n");
					}
				}
				return outputStringBuilder.deleteCharAt(outputStringBuilder.length()-1).toString();
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}
}