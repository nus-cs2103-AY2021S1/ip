public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String done, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = (done == "1");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String splitToString() {
        return "\n" + "D" + "/" + this.ifDone() + "/" + this.description + "/" + this.by;
    }
}