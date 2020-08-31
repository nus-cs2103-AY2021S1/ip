/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected static final String TYPE = "deadline";

    protected String by;

    Deadline(String description, String by) {
        super(description, TYPE);
        this.by = by;
    }

    @Override
    public String getTiming() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
