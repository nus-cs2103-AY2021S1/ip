package duke;

public class Todo extends Task {

	public Todo(String description) {
		super(description);
	}

	public Todo(String description, boolean isDone) {
		super(description, isDone);
	}

	@Override
	public Todo completeTask() {
		return new Todo(description, true);
	}

	@Override
	public String getData() {
		return "TODO#" + description + "#" + String.valueOf(isDone);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
