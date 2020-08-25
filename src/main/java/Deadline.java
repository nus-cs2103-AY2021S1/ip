public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        symbol = "[D]";
        this.by = by;
    }

    @Override
    public String toString() {
        return (symbol + super.toString() + " (by: " + by + ")");
    }
}
