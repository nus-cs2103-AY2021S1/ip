public class Deadline extends Task {
    String deadline;

    public Deadline(String description, String deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    @Override
    public String generateSaveFormat() {
        return super.generateSaveFormat() + Duke.line + deadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}
