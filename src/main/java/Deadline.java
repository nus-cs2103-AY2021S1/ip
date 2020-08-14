public class Deadline extends Task {
    private final String deadline;

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
