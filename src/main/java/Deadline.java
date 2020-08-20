public class Deadline extends Task {
    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + this.description + " (by:" + this.deadline + ")";
    }
}
