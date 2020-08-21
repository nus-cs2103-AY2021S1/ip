public class Deadline extends Task {
    private static final String identifier = "D";

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.identifier, super.toString(), this.by);
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Deadline.identifier, super.serialise(), this.by);
    }
}
