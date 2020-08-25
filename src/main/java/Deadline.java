public class Deadline extends Task {
    private String type = "[D]";
    private String by;
    public Deadline(String isCompleted, String taskName, String by) {
        super(isCompleted, taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
