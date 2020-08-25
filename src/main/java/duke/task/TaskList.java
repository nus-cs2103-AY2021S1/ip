package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {

	ArrayList<Task> tasks;

	public TaskList() {
		tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> tasksForInit) {
		tasks = tasksForInit;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public void deleteTask(int index) {
		tasks.remove(index);
	}

	public void setTaskDone(int index) {
		Task task = tasks.get(index);
		task.markAsDone();
	}

	public Task getTask(int index) throws DukeException {
		if (index >= tasks.size()) {
			throw new DukeException("There are no tasks at index " + (index + 1));
		} else {
			return tasks.get(index);
		}
	}

	public int numberOfTasks() {
		return tasks.size();
	}

	public ArrayList<Task> find(String keyWord) {
		ArrayList<Task> foundTasks = new ArrayList<>();
		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			if (task.toString().contains(keyWord)) {
				foundTasks.add(task);
			}
		}
		return foundTasks;
	}
}
