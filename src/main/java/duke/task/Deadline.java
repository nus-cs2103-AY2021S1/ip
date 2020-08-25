package duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = sanitizeString(by);
    }

    public Deadline(String description, String by) {
        this(description, false, by);
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (by: %s)", by);
    }

    @Override
    protected String taskTypeString() {
        return "D";
    }

    public String getBy() {
        return by;
    }
}
