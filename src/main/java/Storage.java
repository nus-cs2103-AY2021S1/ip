import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Storage ensures that the current tasks are always backed up in a separate file.
 */
public class Storage {

	private final String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Parses in a line from the storage file and returns a Task based on the details retrieved from that line.
	 *
	 * @param display A line from the storage file.
	 * @return Task based on the details retrieved from display.
	 * @throws DukeException
	 */
	private Task addTaskFromStorage(String display) throws DukeException {
		String[] taskDetails = display.split(" \\| ");
		String taskType = taskDetails[0];
		boolean isDone = taskDetails[1].equals("1");
		String description = taskDetails[2];
		if (taskType.equals(TaskType.TODO.getSymbol())) {
			return new ToDo(description, isDone);
		} else if (taskType.equals(TaskType.DEADLINE.getSymbol())) {
			return new Deadline(description, Parser.parseDate(taskDetails[3]), isDone);
		} else if (taskType.equals(TaskType.EVENT.getSymbol())) {
			return new Deadline(description, Parser.parseDate(taskDetails[3]), isDone);
		} else {
			throw new DukeException("I don't know what that means");
		}
	}

	/**
	 * When Duke is just started up, it reads from the storage file, goes through each line, each corresponding to a
	 * task, and returns the tasks.
	 *
	 * @return ArrayList of Tasks according to the storage file.
	 */
	public ArrayList<Task> initializeTasks() {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				return new ArrayList<>();
			}
			Scanner sc = new Scanner(file);
			ArrayList<Task> tasks = new ArrayList<>();
			while (sc.hasNextLine()) {
				tasks.add(addTaskFromStorage(sc.nextLine()));
			}
			return tasks;
		} catch (IOException | DukeException ex) {
			Ui.formatResponse(ex.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Overwrites the current storage file with updated taskList, or creates a new storage file with updated taskList
	 * if it currently does not exist.
	 *
	 * @param taskList Details of TaskList are gotten from.
	 * @throws DukeException
	 */
	public void saveList(TaskList taskList) throws DukeException {
		ArrayList<Task> tasks = taskList.getTasks();
		String[] directories = filePath.split("/");
		int nested = 1;
		File dir = new File(directories[0]);
		if (nested < directories.length) {
			if (!dir.exists()) {
				dir.mkdir();
			}
			dir = new File(String.join("/", Arrays.copyOfRange(directories, 0, nested++)));
		}
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			String contents = "";
			for (Task task : tasks) {
				contents += task.getSavedString() + "\n";
			}
			fw.write(contents);
			fw.close();
		} catch (IOException ex) {
			throw new DukeException("I could not save tasks.");
		}
	}
}
