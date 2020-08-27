public class Event extends Task {
    protected String taskDate;

    public Event(String taskName, String taskDate) {
        super(taskName);
        this.taskDate = taskDate;
    }

    @Override
    public String getDate() {
        return taskDate;
    }

    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), taskName, taskDate);
    }
}
