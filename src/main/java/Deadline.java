public class Deadline extends Task {
    String deadlineDate;

    public Deadline(String deadlineName, String deadlineDate) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + deadlineDate + ")";
    }
}
