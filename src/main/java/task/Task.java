package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Task stores it type, a boolean indicating whether the task has been completed, a string describing the task and a
 * time.
 */

public class Task {
	private TaskType taskType;
	private boolean isDone;
	private String string;
	private Optional<LocalDateTime> dateTime;
	private String tag ;

	public Task(TaskType taskType, boolean isDone, String string) {
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
		this.dateTime = Optional.empty();
		this.tag = "";
	}

	public Task(TaskType taskType, boolean isDone, String string, Optional<LocalDateTime> dateTime) {
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
		this.dateTime = dateTime;
		this.tag = "";
	}

	public Task(TaskType taskType, boolean isDone, String string, String tag) {
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
		this.dateTime = Optional.empty();
		this.tag = tag;
	}

	public Task(TaskType taskType, boolean isDone, String string, Optional<LocalDateTime> dateTime, String tag) {
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
		this.dateTime = dateTime;
		this.tag = tag;
	}

	public String getString() {
		return string;
	}

	public String getDoneString() {
		String string;
		return (isDone) ? "[\u2713]" : "[\u2717]"; // ✓ or ✗
	}

	public Task done() {
		return new Task(taskType, true, string, dateTime, tag);
	}

	public String getTypeString() {
		String string;
		assert taskType != null : "taskType has not been instantiated.";

		if(taskType.equals(TaskType.TODO)) {
			string = "[T]";
		}
		else if(taskType.equals(TaskType.DEADLINE)) {
			string = "[D]";
		}
		else{
			string = "[E]";
		}
		return string;
	}

	public String toString() {
		return string;
	}

	public String toFullOutputString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getTypeString()).append(getDoneString()).append(toString());
		if(!tag.isEmpty()) {
			stringBuilder.append("#").append(tag);
		}
		return stringBuilder.toString();
	}

	public String toFullFileString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getTypeString()).append(getDoneString()).append(toString()).append("#").append(getTag());
		return stringBuilder.toString();
	}

	public String getTag() {
		return (tag.isEmpty()) ? "/n" : tag;
	}


	static public boolean isEmptyTag(String tag) {
		return tag.equals("/n");
	}

	static public String[] getTagFromString(String fullCommand) throws DukeException {
		String[] output = new String[2];
		int tagIndex = fullCommand.indexOf("/tag ");
		if (tagIndex == -1) {
			output[0] = fullCommand;
			output[1] = "";
			return output;
		}
		if (fullCommand.length() <= tagIndex + 5) {
			throw new DukeException("Oh no! Tag description cannot be empty! Remove \"/tag\" instead.");
		}
		String tag = fullCommand.substring(tagIndex + 5);
		if (tag.equals("/n")) {
			throw new DukeException("Oh no! The tag description: \"/n\" is a reserved tag. Please use another tag.");
		}

		String[] tagSubString = tag.split(" ");
		tag = tagSubString[0]; //Only use the first word after /tag
		if (!tag.matches("[A-Za-z0-9]+")) {
			throw new DukeException("Oh no! Tag needs to be alphanumeric!");
		}
		output[0] = fullCommand.substring(0,tagIndex);
		output[1] = tag;
		return output;
	}
}
