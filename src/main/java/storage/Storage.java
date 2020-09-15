package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Storage deals with loading and saving task list from/ to memory.
 */

public class Storage {
	private String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<Task> load() throws DukeException {
		File file = new File(filePath);
		assert file.exists() : "File does not exist.";
		ArrayList<Task> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file); //causing exception
			String task, taskString, tag;
			TaskType taskType;
			boolean isDone;
			LocalDateTime dateTime;
			while (sc.hasNext()) {
				task = sc.nextLine();
				switch (task.charAt((1))) {
					case 'T' :
						taskType = TaskType.TODO;
						break;
					case 'D' :
						taskType = TaskType.DEADLINE;
						break;
					case 'E' :
						taskType = TaskType.EVENT;
						break;
					default :
						throw new DukeException("Oh no! TaskType in file is wrong. File is corrupted!");
				}
				switch (task.charAt((4))) {
					case '\u2713' :
						isDone = true;
						break;
					case '\u2717' :
						isDone = false;
						break;
					default :
						throw new DukeException("Oh no! isDone in file is wrong. File is corrupted!");
				}
				String[] stringAndTag = getTag(task);
				taskString = stringAndTag[0];
				tag = stringAndTag[1];
				int hashIndex = task.lastIndexOf("#");
				taskString = task.substring(6,hashIndex);

				if (taskType == TaskType.DEADLINE) {
					int index = task.indexOf("(by: ");
					if (index == -1) {
						throw new DukeException("Oh no! Deadline task does not have a deadline. File is corrupted!");
					}
					try {
						dateTime = LocalDateTime.parse(task.substring(index + 5, hashIndex - 2), DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
					} catch (DateTimeParseException e ) {
						throw new DukeException("Oh no! File contains incorrect date format. File is corrupted!");
					}
					list.add(new Task(taskType, isDone, taskString, Optional.of(dateTime), tag));
				} else {
					list.add(new Task(taskType, isDone, taskString, tag));
				}
			}

		} catch (FileNotFoundException e) {
			throw new DukeException("File not found.");
		}
		return list;
	}

	public String save(TaskList taskList) {
		File file = new File(filePath);
		assert file.exists() : "File does not exist.";
		if(file.getParentFile() != null) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(filePath);
			Task task;

			for (int listIndex = 0; listIndex < taskList.size(); listIndex++) {
				task = taskList.get(listIndex);
				fileWriter.write(task.toFullFileString() + System.lineSeparator());
			}
			fileWriter.close();

			return "Tasks have been saved!";
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	public String clear() {
		save(new TaskList());
		return "Task file has been cleared!";
	}

	public String[] getTag(String task) throws DukeException {
		int hashIndex = task.lastIndexOf("#");
		if(hashIndex == -1) {
			throw new DukeException("No # found. File is corrupted.");
		}
		if (hashIndex == task.length()) {
			throw new DukeException("No description for tag found. File is corrupted.");
		}
		String[] output = new String[2];
		output[0] = task.substring(0,hashIndex);
		output[1] = task.substring(hashIndex + 1);
		if (Task.isEmptyTag(output[1])) {
			output[1] = "";
		}
		return output;
	}
}