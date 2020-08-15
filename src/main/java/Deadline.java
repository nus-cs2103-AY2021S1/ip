public class Deadline extends Task {
    String deadline;

    Deadline(String label, String deadline) {
        super(label);
        // Remove the "by"
        this.deadline = deadline.substring(3);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
