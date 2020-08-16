public class Deadline extends Task {
    String taskBy;

    Deadline(String taskName, String taskBy) {
        super(taskName);
        this.taskBy = taskBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskBy + ")";
    }
}