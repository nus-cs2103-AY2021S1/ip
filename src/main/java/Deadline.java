public class Deadline extends Task {

    private String deadlineDate;

    public Deadline(String deadlineName, String deadlineDate) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }

    @Override
    public String getSaveFormat() {
        return "D" + " | " + super.getSaveFormat() + " | " + deadlineDate;
    }
}
