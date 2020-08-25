public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getFormattedString() {
        return "D | " + (super.isDone? 1 : 0) + " | " + super.description + " | " +
                by;
    }
}
