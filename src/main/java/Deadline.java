public class Deadline extends Task {
    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public Deadline(String task, boolean isDone, String due) {
        super(task, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
