public class Deadline extends Task {

    protected String deadline;

    protected Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        return "[D][" + done + "] " + this.task + "(by:" + this.deadline + ")";
    }
}
