public class Deadline extends Task{

    private final String by;

    protected Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
