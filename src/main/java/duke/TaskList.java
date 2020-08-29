package duke;

import java.util.ArrayList;

/**
 * Represents the task list.
 */
public class TaskList {
	/** the lists of tasks. */
	private ArrayList<Task> tasks;

	/**
	 * Constructor for TaskList.
	 */
	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	/**
	 * Constructor for TaskList.
	 * @param tasks tasks to be inserted in the list.
	 */
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Gets all the task in the list.
	 * @return the tasks in TaskList.
	 */
	public ArrayList<Task> getTask() {
		return tasks;
	}

	/**
	 * Gets a particular Task from the TaskList.
	 * @param index the index of the task in the list.
	 * @return the Task object in the specified index in the list.
	 */
	public Task getTask(int index) {
		return tasks.get(index);
	}

	/**
	 * Gets the number of Task in TaskList.
	 * @return the number of tasks in the list.
	 */
	public int getSize() {
		return tasks.size();
	}

	/**
	 * Marks a particular Task from the TaskList as done.
	 * @param index the index of the task in the list.
	 */
	public void markDone(int index) {
		tasks.set(index, tasks.get(index).completeTask());
	}

	/**
	 * Removes a particular Task from the TaskList.
	 * @param index the index of the task in the list.
	 */
	public void removeTask(int index) {
		tasks.remove(index);
	}

	/**
	 * Removes all the Task from the TaskList.
	 */
	public void removeAllTasks() {
		tasks = new ArrayList<>();
	}

	/**
	 * Adds a Task into the TaskList.
	 * @param task the task to be added.
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}
}
