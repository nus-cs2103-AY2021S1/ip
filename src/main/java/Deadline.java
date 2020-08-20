public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "\u2713" : "\u2717";
        return String.format("[D][%s] %s (by: %s)", symbol, this.name, this.deadline);
    }
}
