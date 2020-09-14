package duke.storage;

import duke.task.Task;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
			Scanner sc = new Scanner(file);
			String task, taskString;
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
					case '\u2718' :
						isDone = false;
						break;
					default :
						throw new DukeException("Oh no! isDone in file is wrong. File is corrupted!");
				}

				taskString = task.substring(6);
				if (taskType == TaskType.DEADLINE) {
					int index = task.indexOf("(by:");
					dateTime = LocalDateTime.parse(task.substring(index + 4, task.length()-1), DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
					list.add(new Task(taskType, isDone, taskString, Optional.of(dateTime)));
				} else {
					list.add(new Task(taskType, isDone, taskString));
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
				fileWriter.write(task.getTypeString() + task.getDoneString() + task.getString() + System.lineSeparator());
			}
			fileWriter.close();

			return "Tasks have been saved! ";
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}