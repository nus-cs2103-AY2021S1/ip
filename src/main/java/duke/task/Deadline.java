package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description) {
        super(description);
        this.by = null;
    }

    @Override
    public String toString() {
        return by == null
                ? "[D]" + super.toString()
                : "[D]" + super.toString() + " (by: " + by + ")";
    }
}