public class Event extends Task {
	private String eventTime;

	public Event(String taskContent, String eventTime) {
		super(taskContent);
		this.eventTime = eventTime;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
	}
}