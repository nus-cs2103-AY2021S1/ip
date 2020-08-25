import java.util.ArrayList;

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

	@Override
	public String toString() {
		return "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
	}

}
