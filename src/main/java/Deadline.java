public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
