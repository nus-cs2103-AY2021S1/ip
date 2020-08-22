public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = by;
    }

    public String getTiming() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}