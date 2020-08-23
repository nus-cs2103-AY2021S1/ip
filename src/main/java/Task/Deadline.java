package Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        String icon;
        if (this.completed) {
            icon = "[" + "\u2713" + "]";
        } else {
            icon = "[" + "\u2718" + "]";
        }
        return "[D] " + icon + " " + this.description + " (by: " + this.by + ")";
    }
}
