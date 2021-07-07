/**
 * Represents a do after task.
 */
public class DoAfter extends Task {
    protected static final String TYPE = "doafter";

    protected String after;

    DoAfter(String description, String after) {
        super(description, TYPE);
        this.after = after;
    }

    @Override
    public String getTiming() {
        return this.after;
    }

    @Override
    public String toString() {
        return "[DA]" + super.toString() + " (after: " + after + ")";
    }
}
