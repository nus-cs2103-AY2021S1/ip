public class Deadline extends Task {
    protected String deadline;

    Deadline(String desc) {
        super(desc.split(" /by ", 2)[0]);
        this.deadline = desc.split(" /by ", 2)[1];
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.deadline + ")";
    }
}
