package duke;

public class Deadline extends Task {

    protected String by;

    public static final String delimiterBy = " /by ";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", getStatusCode(), description , by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
