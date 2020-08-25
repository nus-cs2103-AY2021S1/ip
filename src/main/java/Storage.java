import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

	private final String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

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

	public ArrayList<Task> initializeTasks() {
		try {
			File file = new File("src/data/duke.txt");
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

	public static void saveList(TaskList taskList) throws DukeException {
		ArrayList<Task> tasks = taskList.getTasks();
		File dir = new File("src/data");
		if (!dir.exists()) {
			dir.mkdir();
		}
		try {
			File file = new File("src/data/duke.txt");
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
