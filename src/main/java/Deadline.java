public class Deadline extends Task {
    protected String by;

    public Deadline(String details, String by) {
        super(details);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
