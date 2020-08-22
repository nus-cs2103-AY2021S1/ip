public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        return "D" + separator + isDone + separator + super.description + separator + by + "\n";
    }
}