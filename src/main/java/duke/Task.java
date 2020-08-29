package duke;

/**
 * Represents a task.
 */
public class Task {
	/** the description of the task. */
	protected final String description;
	/** boolean indicating whether the task is done or not. */
	protected final boolean isDone;

	/**
	 * Constructor for Task.
	 * @param description the description of the task.
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Constructor for Task.
	 * @param description the description of the task.
	 * @param isDone boolean to indicates whether the task has been done or not.
	 */
	public Task(String description, boolean isDone) {
		this.description = description;
		this.isDone = isDone;
	}

	/**
	 * Marks the Task as done.
	 * @return Task with updated status (done).
	 */
	public Task completeTask() {
		return new Task(description, true);
	}

	/**
	 * Gets the status icon of the task.
	 * @return a check icon if the task is done, a cross icon otherwise.
	 */
	public String getStatusIcon() {
		return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
	}

	/**
	 * Gets the format of the Task to be saved in hard disk.
	 * @return Task object in specified format.
	 */
	public String getData() {
		return "TASK#" + description + "#" + String.valueOf(isDone);
	}

	/**
	 * Gets the string representation of the Task object.
	 * @return the string representation of the Task.
	 */
	public String toString() {
		return getStatusIcon() + ' ' + description;
	}
}
