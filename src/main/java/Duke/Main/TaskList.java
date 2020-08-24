package Duke.Main;

import Duke.Errors.InvalidIndexException;
import Duke.Tasks.Task;

import java.util.List;

public class TaskList {
	Storage storage;

	public TaskList(Storage storage) {
		this.storage = storage;
		/*
		- Values are constantly updated to prevent User's data
			from disappearing if they quit halfway through.
		- This however reduces the efficiency as it has to
			pull and push to the txt file each time.
		*/
	}

	public List<Task> getList() throws Exception {
		return storage.getFileContents();
	}

	/**
	 * Pulls from txt file into a Task object
	 * @param i Line to get
	 * @return Task object
	 * @throws Exception
	 */
	public Task getTask(int i) throws Exception {
		List<Task> filecontents = storage.getFileContents();
		if (i <= 0 || i > filecontents.size()) {
			throw new InvalidIndexException();
		}
		return filecontents.get(i - 1);
	}

	/**
	 * Adds a Task into txt file as a string
	 * @param arr Array of respective values representing a Task
	 * @throws Exception
	 */
	public void addTask(String[] arr) throws Exception {
		storage.appendToFile(Task.stringFormat(arr));
	}

	/**
	 * Completes a Task
	 * @param i Line to update
	 * @return Completed Task object
	 * @throws Exception
	 */
	public Task completeTask(int i) throws Exception {
		List<Task> filecontents = storage.getFileContents();
		Task t = filecontents.get(i - 1);
		if (i <= 0 || i > filecontents.size()) {
			throw new InvalidIndexException();
		}
		filecontents.get(i - 1).setStatus("1");
		storage.rewriteFileContents(filecontents);
		return t;
	}

	/**
	 * Deletes a Task
	 * @param i Line to delete
	 * @return Delete Task object
	 * @throws Exception
	 */
	public Task deleteTask(int i) throws Exception {
		List<Task> filecontents = storage.getFileContents();
		Task t = filecontents.get(i - 1);
		if (i <= 0 || i > filecontents.size()) {
			throw new InvalidIndexException();
		}
		filecontents.remove(i - 1);
		storage.rewriteFileContents(filecontents);
		return t;
	}

	/**
	 * Gets total number of lines on txt
	 * @return
	 * @throws Exception
	 */
	public int getSize() throws Exception {
		List<Task> filecontents = storage.getFileContents();
		return filecontents.size();
	}
}