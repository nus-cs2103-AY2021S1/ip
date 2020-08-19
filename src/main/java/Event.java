public class Event extends Task {
    protected String taskDate;

    public Event(int taskNum, String taskName, String taskDate) {
        super(taskNum, taskName);
        this.taskDate = taskDate;
    }

    public String toString() {
        return String.format("%d. [E][%s] %s (at: %s)", taskNum, getStatusIcon(), taskName, taskDate);
    }
}
