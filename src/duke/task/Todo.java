package duke.task;

public class Todo extends Task {

	public Todo(String name) {
		super(name);
	}

	public Todo(String name, String completed) {
		super(name, completed);
	}

	@Override
	public String toString() {
		return String.format("[T] %s", super.toString());
	}

}
