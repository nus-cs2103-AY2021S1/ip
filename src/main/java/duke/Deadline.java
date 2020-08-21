package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String showTask() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.showTask(), this.by);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.by;
    }
}
