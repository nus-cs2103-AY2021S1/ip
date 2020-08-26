public class Deadline extends Task {
    protected String by;
    final static protected String STATUS = "D";

    public Deadline(String description, String by) {
        super(description,"D");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}