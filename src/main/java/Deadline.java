public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        return "D" + " | " + completionStatus + " | " + this.description + " | "
                + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }
}