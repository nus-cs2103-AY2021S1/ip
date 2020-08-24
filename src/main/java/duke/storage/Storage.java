package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	static String SAVED_TASK_PATH;
	public Storage(String path) {
		SAVED_TASK_PATH = path;
	}

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
			throw new DukeException("Tried to find " + fullPath + "\n"
					+ "but File/Directory at path " + fileInDirectory.getAbsolutePath()
					+ " could not be found.");
		} else {
			return true;
		}
	}

	public void updateStorage(String textForUpdate) throws DukeException {
		try {
			FileWriter fw = new FileWriter(SAVED_TASK_PATH);
			fw.write(textForUpdate);
			fw.close();
		} catch (IOException e) {
			throw new DukeException("Could not write to file");
		}
	}

	void createFile() throws DukeException {
		File newFile = new File(SAVED_TASK_PATH);
		try {
			if (newFile.createNewFile()) {
				// successfully created file
			} else {
				throw new DukeException("Save file already exists at " + newFile.getAbsolutePath());
			}
		} catch (IOException e) {
			throw new DukeException("Could not create a save file at " + newFile.getAbsolutePath());
		}
	}

}
