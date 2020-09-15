package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskType;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Optional;

/**
 * Deadline Command
 * Requires a description of the deadline and a valid date for the task.
 */

public class CommandDeadline implements Command {
	private final String fullCommand;

	public CommandDeadline(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		assert fullCommand.length() <= 9 : "deadline: string is too short.";
		String input = fullCommand.substring(8);
		try {
			String[] inputAndTag = Task.getTagFromString(input);
			input = inputAndTag[0];
			String tag = inputAndTag[1];
			int index = input.indexOf("/by ");

			//No /by
			if (!hasDate(index)) {
				return "☹ OOPS!!! The description of a deadline must have a indicated deadline.";
			}

			//No description
			if (!hasDescription(index, input)) {
				return "☹ OOPS!!! The description of a deadline cannot be empty.";
			}

			if (hasRestrictedPhrase(input)) {
				return "☹ OOPS!!! Restricted phrase used";
			}

			String dateTimeString = toLocalDateTimeReadible(index, input);
			StringBuilder outputStringBuilder = new StringBuilder();
			//Try to input date if it a valid is date is used.
			try {
				LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

				String taskString = toTaskString(index, input, localDateTime);

				Task task = new Task(TaskType.DEADLINE, false, taskString, Optional.of(localDateTime), tag);
				taskList.add(task);

				outputStringBuilder.append("Got it. I've added this task:").append("\n");

				outputStringBuilder.append(" ").append(task.toFullOutputString()).append("\n");

				outputStringBuilder.append("Now you have " + taskList.size() + " tasks in the list.");

				return outputStringBuilder.toString();
			} catch (DateTimeParseException e) {

				outputStringBuilder
						.append("☹ OOPS!!! The description of a deadline must have a valid date and time. ")
						.append("(Format: /by dd/mm/yyyy tttt e.g 2/12/2019 1800");
				return outputStringBuilder.toString();
			}
		} catch (DukeException e) {
			return e.getMessage();
		}
	}

	@Override
	public boolean isExit() {
		return false;
	}

	public boolean hasDate(int index) {
		return (index != -1);
	}

	public boolean hasDescription(int index, String input) {
			return (index > 1) && !input.isEmpty();
	}

	public boolean hasRestrictedPhrase(String input) {
		return input.contains("(by: ");
	}

	public String toLocalDateTimeReadible(int index, String input) {
		StringBuilder dateStringBuilder = new StringBuilder();
		String[] strings;
		if(input.length() < index + 4) { //checks for Exception
			return "";
		}

		strings = input.substring(index + 4).split("/");
		if (strings.length < 3) {
			return "";
		}
		if (strings[2].length() < 9 ) {
			return "";
		}
		if(strings[0].length() < 2) {
	 		strings[0] = "0" + strings[0];
		}
		if(strings[1].length() < 2) {
			strings[1] = "0" + strings[1];
		}

		dateStringBuilder.append(strings[2].substring(0, 4))
				.append("-").append(strings[1])
				.append("-").append(strings[0])
				.append("T")
				.append(strings[2].substring(5, 7))
				.append(":")
				.append(strings[2].substring(7, 9));
		return dateStringBuilder.toString();
	}

	public String toTaskString(int index, String input,LocalDateTime localDateTime) {
		StringBuilder taskStringBuilder = new StringBuilder();
		taskStringBuilder.append(input.substring(0, index))
				.append("(by: ")
				.append(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)))
				.append(") ");
		return taskStringBuilder.toString();
	}
}
