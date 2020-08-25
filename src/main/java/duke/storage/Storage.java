package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

	static String SAVED_TASK_PATH;

	/**
	 * Initializes Storage with the input path as the location for the storage file.
	 *
	 * @param path The path to store the storage file.
	 */
	public Storage(String path) {
		SAVED_TASK_PATH = path;
	}

	/**
	 * Returns an ArrayList of tasks from the storage file.
	 *
	 * @return The ArrayList of tasks from the storage file.
	 * @throws DukeException If the storage file cannot be read, found or created if not created yet.
	 */
	public ArrayList<Task> load() throws DukeException {
		return readAndLoadFromFile();
	}

	Task createTaskFromFile(String[] strArray) throws DukeException {
		try {
			boolean done = strArray[1].equals("1") ? true : false;
			String description = strArray[2];
			Task task;
			if (strArray[0].equals("T")) {
				task = new ToDo(description);
			} else if (strArray[0].equals("D")) {
				String date = strArray[3];
				String time = strArray[4];
				LocalDate localDate = LocalDate.parse(date);
				LocalTime localTime = LocalTime.parse(time);
				task = new Deadline(description, localDate, localTime);
			} else if (strArray[0].equals("E")) {
				String date = strArray[3];
				String time = strArray[4];
				LocalDate localDate = LocalDate.parse(date);
				LocalTime localTime = LocalTime.parse(time);
				task = new Event(description, localDate, localTime);
			} else {
				throw new DukeException("Saved file task type cannot be understood.");
			}

			if (done) {
				task.markAsDone();
			}

			return task;
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Saved file text format error");
		}
	}

	ArrayList<Task> readAndLoadFromFile() throws DukeException {
		try {
			ArrayList<Task> taskList = new ArrayList<>();
			if (fileExists(SAVED_TASK_PATH)) {
				File file = new File(SAVED_TASK_PATH);
				//System.out.println(file.getAbsolutePath());
				Scanner s = new Scanner(file);
				while (s.hasNext()) {
					String[] strArray = s.nextLine().split(" \\| ");
					Task task = createTaskFromFile(strArray);
					taskList.add(task);
				}
				return taskList;
			} else {
				createFile();
				return new ArrayList<Task>();
			}
		} catch (IOException e) {
			throw new DukeException(e.getMessage());
		} catch (DukeException e) {
			throw new DukeException(e.getMessage());
			// fix input file?
		}
	}

	boolean fileExists(String path) throws DukeException {
		String fullPath = new File(path).getAbsolutePath();
		String[] pathStringArray = path.split("/");
		StringBuilder currentPath = new StringBuilder();
		boolean directoryNotFound = false;
		File fileInDirectory = null;
		for (int i = 0; i < pathStringArray.length; i++) {
			if (i == 0) {
				currentPath.append(pathStringArray[i]);
			} else {
				currentPath.append("/" + pathStringArray[i]);
			}
			fileInDirectory = new File(currentPath.toString());
			if (!fileInDirectory.exists()) {
				directoryNotFound = true;
				break;
			}
		}

		if (directoryNotFound) {
			return false;
		} else {
			return true;
		}
	}

	public void updateStorage(TaskList tasks) throws DukeException {
		try {
			FileWriter fw = new FileWriter(SAVED_TASK_PATH);
			String textForUpdate = convertTaskListToSaveFormat(tasks);
			fw.write(textForUpdate);
			fw.close();
		} catch (IOException e) {
			throw new DukeException("Could not write to file");
		}
	}

	void createFile() throws DukeException {
		File newFile = new File(SAVED_TASK_PATH);
		try {
			String[] pathStringArray = SAVED_TASK_PATH.split("/");
			StringBuilder currentPath = new StringBuilder();
			newFile.getParentFile().mkdirs();
			if (newFile.createNewFile()) {
				// successfully created new file
			} else {
				throw new DukeException("Could not create a save file at " + newFile.getAbsolutePath());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new DukeException("Could not create a save file at " + newFile.getAbsolutePath());
		}
	}

	String convertTaskListToSaveFormat(TaskList taskList) throws DukeException {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < taskList.numberOfTasks(); i++) {
			Task task = taskList.getTask(i);
			String saveTaskString = convertTaskToSaveFormat(task);
			stringBuilder.append(saveTaskString + "\n");
		}

		return stringBuilder.toString();
	}

	String convertTaskToSaveFormat(Task task) throws DukeException {
		String taskType;
		String description = task.getDescription();
		int taskDone = task.isDone() ? 1 : 0;
		String resultString;
		if (task instanceof ToDo) {
			taskType = "T";
			resultString = taskType + " | " + taskDone + " | " + description;
		} else if (task instanceof Deadline) {
			Deadline deadline = (Deadline) task;
			taskType = "D";
			String date = deadline.getLocalDate().toString();
			String time = deadline.getLocalTime().toString();
			resultString = taskType + " | " + taskDone + " | " + description + " | " + date + " | " + time;
		} else if (task instanceof Event) {
			Event event = (Event) task;
			taskType = "E";
			String date = event.getLocalDate().toString();
			String time = event.getLocalTime().toString();
			resultString = taskType + " | " + taskDone + " | " + description + " | " + date + " | " + time;
		} else {
			throw new DukeException("Unable to save task, unknown task type");
		}

		return resultString;
	}

}
