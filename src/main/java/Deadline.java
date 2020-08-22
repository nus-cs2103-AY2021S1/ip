public class Deadline extends Task {

   protected String by;

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + " (by: " + by + ")";
    }
}