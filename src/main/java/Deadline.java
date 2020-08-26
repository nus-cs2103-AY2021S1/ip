public class Deadline extends Task {
	private String deadline;

	public Deadline(String taskContent, String deadline) {
		super(taskContent);
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.deadline + ")";
	}
}