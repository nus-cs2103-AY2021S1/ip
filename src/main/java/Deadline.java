public class Deadline extends Task {
    protected String taskDate;

    public Deadline(String taskName, String taskDate) {
        super(taskName);
        this.taskDate = taskDate;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), taskName, taskDate);
    }
}
