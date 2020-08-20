public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String summary, String deadline) {
        super(summary);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}