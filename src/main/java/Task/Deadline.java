package Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.completed = isDone;
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

    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "D>" + completedBinary + ">" + this.description + ">" + this.by;
    }
}
