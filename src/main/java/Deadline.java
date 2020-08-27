public class Deadline extends Task {
	protected final String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	public Deadline(String description, boolean isDone, String by) {
		super(description, isDone);
		this.by = by;
	}

	@Override
	public Deadline completeTask() {
		return new Deadline(description, true, by);
	}

	@Override
	public String getData() {
		return "DEADLINE#" + description + "#" + String.valueOf(isDone) + "#" + by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}
}
