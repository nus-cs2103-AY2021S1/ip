public class Deadline extends Task {
    // Deadlines: Tasks that need to be done before a specific time date/time
    // Example: Submit report by 11/10/2019 5pm
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
