public class Deadline extends Task {
    String deadlineTime;
    public Deadline(String description, String date) {
        super(description);
        this.deadlineTime = date;
    }

    @Override
    public String taskRow() {
        return "[D]" + " " + getStatusIcon() + " " + this +
                "(by: " + deadlineTime + ")";
    }
}
