public class Deadline extends Task {

    // Attributes
    protected String deadline;

    // Constructor
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    // String Representation
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}