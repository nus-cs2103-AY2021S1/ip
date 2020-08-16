public class DeadlineTask extends Task {
    String deadline;

    DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }

}
