public class Task {
	private boolean isDone;
	private String taskContent;

	public Task(String taskContent) {
		this.taskContent = taskContent;
		this.isDone = false;
	}
	public void markAsDone() {
		this.isDone = true;
	}
	@Override
	public String toString() {
		if (isDone) {
			return "[" + "\u2713" + "] " + this.taskContent; 
		} else {
			return "[" + "\u2718" + "] " + this.taskContent;
		}
	}
}