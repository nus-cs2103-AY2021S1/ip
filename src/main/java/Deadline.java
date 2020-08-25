/**
 * Represents a deadline task.
 */

public class Deadline extends Task {
    protected String by;

    static final String TYPE = "deadline";

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
