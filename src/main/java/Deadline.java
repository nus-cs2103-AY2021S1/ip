public class Deadline extends Task {
    private final String deadline;

    public Deadline(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneSymbol, getName(), deadline);
    }

    @Override
    public String toSaveString() {
        return String.format("D|%d|%s|%s", isDone() ? 1 : 0, getName(), deadline);
    }

}
