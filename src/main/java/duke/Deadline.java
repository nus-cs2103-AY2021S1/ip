package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String showTask() {
        return String.format("[D]%s (by: %s)", super.showTask(), this.by);
    }

}
