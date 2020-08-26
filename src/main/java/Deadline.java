public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return String.format("[D]%s (by: %s)", super.toString() , this.deadline);
    }
}
