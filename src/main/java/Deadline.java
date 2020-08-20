public class Deadline extends Task {

    protected final String symbol = "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return (symbol + super.toString() + " (by: " + by + ")");
    }
}
