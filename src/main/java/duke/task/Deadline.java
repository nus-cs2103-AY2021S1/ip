package duke.task;

public class Deadline extends Task {
    protected String deadlineDate;
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public String formatDeadline() {
        return String.format("D | %d | %s | %s",
                this.getIsDone() ? 1 : 0, this.getDescription(), this.deadlineDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }

}