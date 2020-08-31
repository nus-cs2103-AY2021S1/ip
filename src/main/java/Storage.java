package duke;

import duke.Task;
import duke.DukeException;
import duke.TaskList;

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


public class Storage{
	private String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<Task> load() throws DukeException {
		File file = new File(filePath);
		ArrayList<Task> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			String task, taskString;
			duke.TaskType taskType;
			boolean isDone;
			LocalDateTime dateTime;
			while(sc.hasNext()) {
				task = sc.nextLine();
				if(task.charAt(1) == 'T') {
					taskType = duke.TaskType.TODO;
				}
				else if(task.charAt(1) == 'D') {
					taskType = duke.TaskType.DEADLINE;
				}
				else {
					taskType = duke.TaskType.EVENT;
				}

				if(task.charAt(4) == '✓') {
					isDone = true;
				}
				else {
					isDone = false;
				}
				taskString = task.substring(6);
				if(taskType == duke.TaskType.DEADLINE){
					int index = task.indexOf("(by:");
					dateTime = LocalDateTime.parse(task.substring(index + 4, task.length()-1), DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
					list.add(new Task(taskType, isDone, taskString, Optional.of(dateTime)));
				}
				else {
					list.add(new Task(taskType, isDone, taskString));
				}
			}

		} catch (FileNotFoundException e) {
			throw new DukeException();
		}
		return list;
	}

	public String save(TaskList taskList) {
		File file = new File(filePath);
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