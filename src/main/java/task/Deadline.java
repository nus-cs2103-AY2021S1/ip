package task;

public class Deadline extends Task {

    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline + ")";
    }

    @Override
    public String getDescriptionForDatabase() {
        return "deadline - " + (this.isTaskDone() ? "1" : "0") + " - " +
                this.getDescription() + " - " + this.deadline;
    }
}
