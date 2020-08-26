public class Event extends Task {
    private static final String SAVE_STRING = "EVENT|%s|%s|%s";
    private String timeRange;

    public Event(String taskName, String timeRange) {
        super(taskName);
        this.timeRange = timeRange;
    }

    public Event(boolean isDone, String taskName, String timeRange) {
        super(isDone, taskName);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }

    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName, this.timeRange);
    }
}
