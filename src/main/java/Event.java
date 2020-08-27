public class Event extends Task {
	protected final String at;

	public Event(String description, String at) {
		super(description);
		this.at = at;
	}

	public Event(String description, boolean isDone, String at) {
		super(description, isDone);
		this.at = at;
	}

	@Override
	public Event completeTask() {
		return new Event(description, true, at);
	}

	@Override
	public String getData() {
		return "EVENT#" + description + "#" + String.valueOf(isDone) + "#" + at;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + at + ")";
	}
}
