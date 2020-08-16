public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String description, String date) {
        super(description);
        this.deadlineTime = date;
    }

    @Override
    public String taskRow() {
        return "[D]" + super.taskRow() + "(by: " + deadlineTime + ")";
    }
}
