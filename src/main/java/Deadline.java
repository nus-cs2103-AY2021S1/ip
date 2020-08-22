public class Deadline extends Task {

    private TimePoint deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = TimePoint.of(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }
}
