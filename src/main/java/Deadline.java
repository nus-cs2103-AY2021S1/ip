public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    String[] parts = description.split("/by ");
    String[] subparts = parts[0].split("deadline");

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + subparts[1] + " (by: " + by + ")";
    }
}