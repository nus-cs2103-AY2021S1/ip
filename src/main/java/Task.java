public class Task {
	protected final String description;
	protected final boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	public Task(String description, boolean isDone) {
		this.description = description;
		this.isDone = isDone;
	}

	public Task completeTask() {
		return new Task(description, true);
	}

	public String getStatusIcon() {
		return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
	}

	public String getData() {
		return "TASK#" + description + "#" + String.valueOf(isDone);
	}

	public String toString() {
		return getStatusIcon() + ' ' + description;
	}
}
