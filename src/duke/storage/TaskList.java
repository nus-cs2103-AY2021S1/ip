package duke.storage;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
	private final ArrayList<Task> tasks;

	TaskList() {
		tasks = new ArrayList<>();
	}

	public Task remove(int index) {
		return tasks.remove(index);
	}

	public void add(Task task) {
		tasks.add(task);
	}

	public ArrayList<Task> list() {
		return tasks;
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	public Task complete(int index) {
		Task toComplete = tasks.get(index);
		toComplete.setCompleted();
		return toComplete;
	}
}
