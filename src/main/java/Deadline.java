public class Deadline extends Task {
    protected String taskDate;

    public Deadline(int taskNum, String taskName, String taskDate) {
        super(taskNum, taskName);
        this.taskDate = taskDate;
    }

    public String toString() {
        return String.format("%d. [D][%s] %s (by: %s)", taskNum, getStatusIcon(), taskName, taskDate);
    }
}
