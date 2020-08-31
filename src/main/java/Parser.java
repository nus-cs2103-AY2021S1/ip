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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				Task task;
				ui.showOutput("Here are the tasks in your list:");
				for (int i = 1; i <= taskList.size(); i++) {
					task = taskList.get(i - 1);
					ui.showOutput(i + "." + task.getTypeString() + task.getDoneString() + task.getString());
				}
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				ui.showOutput("Bye. Hope to see you again soon!");
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(4);
				if (input.isEmpty()) {
					ui.showOutput("☹ OOPS!!! The description of a todo cannot be empty.");
				} else {
					duke.Task task = new duke.Task(duke.TaskType.TODO, false, input);
					taskList.add(task);
					ui.showOutput("Got it. I've added this task: ");
					ui.showOutput(" " + task.getTypeString() + task.getDoneString() + input);
					ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
				}
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(8);
				if (input.isEmpty()) {
					ui.showOutput("☹ OOPS!!! The description of a deadline cannot be empty.");
					return;
				}
				int index = input.indexOf("/by");
				if (index == -1) {
					ui.showOutput("☹ OOPS!!! The description of a deadline must have a indicated deadline.");
					return;
				}
				StringBuilder stringBuilder = new StringBuilder();
				String[] strings = new String[1];
				if(input.length() >= index + 4) {
					strings = input.substring(index + 4).split("/");
				}
				if( (strings.length >= 3) && (strings[2].length() >= 9) ) {
					if(strings[0].length() < 2){
						strings[0] = "0" + strings[0];
					}
					stringBuilder.append(strings[2].substring(0, 4))
							.append("-").append(strings[1])
							.append("-").append(strings[0])
							.append("T")
							.append(strings[2].substring(5, 7))
							.append(":")
							.append(strings[2].substring(7, 9));
				}
				try{
					LocalDateTime localDateTime = LocalDateTime.parse(stringBuilder.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
					stringBuilder = new StringBuilder();
					stringBuilder.append(input.substring(0, index))
							.append("(by: ")
							.append(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)))
							.append(")");
					input = stringBuilder.toString();
					duke.Task task = new duke.Task(duke.TaskType.DEADLINE, false, input, Optional.of(localDateTime));
					taskList.add(task);
					ui.showOutput("Got it. I've added this task: ");
					ui.showOutput(" " + task.getTypeString() + task.getDoneString() + input);
					ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
				} catch(DateTimeParseException e) {
					ui.showOutput("☹ OOPS!!! The description of a deadline must have a valid date and time. (Format: /by dd/mm/yyyy tttt e.g 2/12/2019 1800");
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(5);
				if(input.isEmpty()){
					ui.showOutput("☹ OOPS!!! The description of a event cannot be empty.");
				}else {
					int index = input.indexOf("/at");
					if (index == -1) {
						ui.showOutput("☹ OOPS!!! The description of an event must have a indicated deadline.");
					} else {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(")");
						input = stringBuilder.toString();
						duke.Task task = new duke.Task(duke.TaskType.DEADLINE, false, input);
						taskList.add(task);
						ui.showOutput("Got it. I've added this task: ");
						ui.showOutput(" " + task.getTypeString() + task.getDoneString() + input);
						ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
					}
				}
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(4);
				if(input.isEmpty()){
					ui.showOutput("☹ OOPS!!! Please indicate which task is done.");
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
							ui.showOutput("☹ OOPS!!! Task number is not found in the list.");
						}
						else{
							duke.Task task =taskList.remove(tasknumber - 1);
							duke.Task newTask = new duke.Task(task.taskType, true, task.toString());
							taskList.add(tasknumber-1, newTask);
							ui.showOutput("Nice! I've marked this task as done: ");
							ui.showOutput(" " + newTask.getTypeString() + newTask.getDoneString() + newTask.toString());
						}
					}
					else{
						ui.showOutput("☹ OOPS!!! Incorrect entry for finished task.");
					}
				}
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				String input = string.substring(6);
				if(input.isEmpty()){
					ui.showOutput("☹ OOPS!!! Please indicate which task is done.");
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
							ui.showOutput("☹ OOPS!!! Task number is not found in the list.");
						}
						else{
							duke.Task task = taskList.remove(tasknumber - 1);
							ui.showOutput("Noted. I've removed this task:");
							ui.showOutput(" " + task.getTypeString() + task.getDoneString() + task.toString());
							ui.showOutput("Now you have " + taskList.size() + " tasks in the list.");
						}
					}
					else{
						ui.showOutput("☹ OOPS!!! Incorrect entry for finished task.");
					}
				}
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
			public void execute(TaskList taskList, Ui ui, Storage storage) {
				ui.showOutput(storage.save(taskList));
			}

			@Override
			public boolean isExit() {
				return false;
			}
		};
	}
}