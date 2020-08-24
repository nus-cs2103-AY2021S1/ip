public class Task {

	String name;
	Boolean completed;

	Task(String name) {
		this.name = name;
		this.completed = false;
	}

	Task(String name, String completed) {
		this.name = name;
		this.completed = completed.equals("1");
	}

	@Override
	public String toString() {
		if (completed) {
			return String.format("[✓] %s", name);
		} else {
			return String.format("[✗] %s", name);
		}
	}

	public void setCompleted() {
		completed = !completed;
	}

	public String[] toArray() {
		String[] strings = new String[3];
		strings[0] = "[T]";
		strings[1] = completed ? "1" : "0";
		strings[2] = name;
		return strings;
	}

}
