public class Deadline extends Task {
    private final String byTime;

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}
