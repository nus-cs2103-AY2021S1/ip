public class Deadline extends Task {
    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.complete
                ? String.format("[D][\u2713] %s (by: %s)", this.title, this.deadline)
                : String.format("[D][\u2717] %s (by: %s)", this.title, this.deadline);
    }
}