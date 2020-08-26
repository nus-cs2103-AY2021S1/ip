package Duke.task;

public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String by) {
        super(description);
        this.time = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
            " (by: " + time + ")";
    }
}
