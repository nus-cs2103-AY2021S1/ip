public class EventTask extends Task {
    private String timeRange;
    private static final String SAVE_STRING = "EVENT|%s|%s|%s";

    public EventTask(String taskName, String timeRange) {
        super(taskName);
        this.timeRange = timeRange;
    }

    public EventTask(boolean isDone, String taskName, String timeRange) {
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
