public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        super.type = Task.Type.DEADLINE;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.description + this.getDeadline();
    }

    protected String getDeadline() {
        return " (by: " + this.deadline + ")";
    }
}