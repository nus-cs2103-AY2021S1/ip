package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> tasks;

	public TaskList() {
		tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public int size() {
		return tasks.size();
	}

	public Task get(int i) {
		return tasks.get(i);
	}

	public boolean add(Task task){
		tasks.add(task);
		return true;
	}

	public void add(int index, Task task){
		tasks.add(index, task);
	}

	public Task remove(int index){
		return tasks.remove(index);
	}

}