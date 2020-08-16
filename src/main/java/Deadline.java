public class Deadline extends Task {
    private String deadline;

    private Deadline (String task, String deadline, boolean isDone) {
        super(task, isDone);
        this.deadline = deadline;
    }

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(task, deadline, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}