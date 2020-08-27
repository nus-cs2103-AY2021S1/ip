package duke;
public class Deadline extends Task {
    String description;
    String deadline;
    boolean isDone;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + deadline + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "|" + this.deadline;
    }
}
