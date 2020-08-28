package duke;

import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<Task> getTask() {
		return tasks;
	}

	public Task getTask(int index) {
		return tasks.get(index);
	}

	public int getSize() {
		return tasks.size();
	}

	public void markDone(int index) {
		tasks.set(index, tasks.get(index).completeTask());
	}

	public void removeTask(int index) {
		tasks.remove(index);
	}

	public void removeAllTasks() {
		tasks = new ArrayList<>();
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
}
