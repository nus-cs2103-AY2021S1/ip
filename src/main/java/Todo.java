public class Todo extends Task {

	Todo(String name) {
		super(name);
	}

	Todo(String name, String completed) {
		super(name, completed);
	}

	@Override
	public String toString() {
		return String.format("[T] %s", super.toString());
	}

}
