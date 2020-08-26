import java.util.ArrayList;
import java.util.Date;

public class TaskList {
	private final ArrayList<Task> tasks;

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public Task getTask(int idx) throws DukeException {
		try {
			return tasks.get(idx);
		} catch (IndexOutOfBoundsException ex) {
			throw new DukeException("task index out of bounds");
		}
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void markAsDone(int idx) throws DukeException {
		getTask(idx).markAsDone();
	}

	public Task removeTask(int idx) throws DukeException {
		try {
			return tasks.remove(idx);
		} catch (IndexOutOfBoundsException ex) {
			throw new DukeException("task index out of bounds");
		}
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public int getCount() {
		return tasks.size();
	}

	public ArrayList<String> toString(Date date) {
		ArrayList<String> lst = new ArrayList<>();
		int i = 1;
		for (Task task: tasks) {
			if (date == null || task.isOccuringOn(date)) {
				lst.add((i++) + ". " + task.toString());
			}
		}
		return lst;
	}
}
