package duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (by: %s)", by);
    }

    @Override
    protected String taskTypeString() {
        return "D";
    }
}
